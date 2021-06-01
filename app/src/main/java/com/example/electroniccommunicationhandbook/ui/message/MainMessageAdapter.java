package com.example.electroniccommunicationhandbook.ui.message;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.database.RoomDB;
import com.example.electroniccommunicationhandbook.entity.Account;
import com.example.electroniccommunicationhandbook.entity.Message;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.repository.MessageRepository;
import com.example.electroniccommunicationhandbook.service.MessageService;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMessageAdapter extends RecyclerView.Adapter<MainMessageAdapter.ViewHolder> {
    private ArrayList<Message> messageList;
    public Context mcontext;
    //student list is used for searching function
    private ArrayList<Student> studentList;
    private ArrayList<Student> allStudentArrayList;
    public recyclerViewClickListener clickListener;
    public MessageRepository messageRepository;
    private MessageService messageService;
    private ArrayList<Teacher> teacherList;
    MutableLiveData<ArrayList<Student>> studentMultableList;
    private UserLocalStore user;
    private Account currenAccount;
    Student student ;
    String name;
    Teacher teacher;

    public MainMessageAdapter(ArrayList<Message> messageList, ArrayList<Student> studentList,
                              ArrayList<Teacher> teacherList,
                              recyclerViewClickListener clickListener, Context context,
                              MessageRepository messageRepository, Account currentAccount) {
        this.messageList = messageList;
        this.mcontext = context;
        this.studentList = studentList;
        this.teacherList=teacherList;
        this.clickListener = clickListener;
        this.messageRepository = messageRepository;
        this.currenAccount = currentAccount;
        this.messageService = messageRepository.getMessageService();
    }

    /*
    class holds structure of view
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ShapeableImageView personalImage;
        public TextView tvName;
        public TextView tvLastestMessage;
        public ConstraintLayout allBorder;

        public ViewHolder(View itemView) {
            super(itemView);

            personalImage = (ShapeableImageView) itemView.findViewById(R.id.imgViewPersonalImage);
            tvName = (TextView) itemView.findViewById(R.id.tvUserName_Message);
            tvLastestMessage = (TextView) itemView.findViewById(R.id.tvLastestMessage);
            allBorder = (ConstraintLayout) itemView.findViewById(R.id.allBorder_message);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View messageView = layoutInflater.inflate(R.layout.item_in_main_message, parent, false);

        ViewHolder viewHolder = new ViewHolder(messageView);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if (messageList != null)
            return messageList.size();
        else if (studentList != null)
            return studentList.size();
        else if(teacherList!=null)
            return teacherList.size();
        else
            return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //list recent message
        if (this.messageList != null) {
            Message message = messageList.get(position);

////        holder.personalImage.setS
            int otherAccountID = 0;
            if (currenAccount.getAccountID() != message.getReceiverAccount().getAccountID()) {
                otherAccountID = message.getReceiverAccount().getAccountID();
            } else {
                otherAccountID = message.getSenderAccount().getAccountID();
            }


            holder.tvLastestMessage.setText(message.getContent());
            holder.tvLastestMessage.setVisibility(View.VISIBLE);
            holder.allBorder.setBackgroundResource(R.drawable.item_message_border);

            //get receiver's name
            name = "";
            holder.tvName.setText("");
            setStudentName(otherAccountID, holder.tvName);

            if(holder.tvName.getText().toString().isEmpty()){
                setTeacherName(otherAccountID, holder.tvName);
            }

            //if other user is a student
            student = new Student();
            Call<Student> studentCall = messageRepository.getMessageService().getStudentByAccountId(otherAccountID);
            studentCall.enqueue(new Callback<Student>() {
                @Override
                public void onResponse(Call<Student> call, Response<Student> response) {
                    if (response.isSuccessful()) {
                        student = response.body();
                        if (student != null) {
                            name= student.getName();
//                            setText(holder.tvName, student.getName());
                            holder.tvName.setText(student.getName());
                        }
                    }
                }

                @Override
                public void onFailure(Call<Student> call, Throwable t) {
                    Log.e("Mess", "Get name: fail");
                }
            });
        } else if (studentList!=null){  //list user (for searching user)
            Student student = this.studentList.get(position);

            holder.tvName.setText(student.getName());

            //invisible lastest message
            holder.tvLastestMessage.setVisibility(View.GONE);
            holder.allBorder.setBackgroundResource(R.drawable.no_border_message);

        }
        else if(teacherList!=null){ //list teacher for searching in student role
            Teacher teacher = this.teacherList.get(position);

            holder.tvName.setText(teacher.getName());

            //invisible lastest message
            holder.tvLastestMessage.setVisibility(View.GONE);
            holder.allBorder.setBackgroundResource(R.drawable.no_border_message);
        }
    }

    /*
    process for clicking item in the recyclerView
     */
    public interface recyclerViewClickListener {
        void onClick(View view, int position);
    }

    /*
    get student's name
     */
    public void setStudentName(int accountid, TextView tvName) {
        Call<ArrayList<Student>> call = messageRepository.getMessageService().getAllStudent();
        call.enqueue(new Callback<ArrayList<Student>>() {
            @Override
            public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                allStudentArrayList = new ArrayList<>();
                allStudentArrayList = response.body();
                if(allStudentArrayList!=null) {
                    for (int i = 0; i < allStudentArrayList.size(); i++) {
                        if (allStudentArrayList.get(i).getAccount().getAccountID() == accountid) {
                            tvName.setText(allStudentArrayList.get(i).getName());
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Student>> call, Throwable t) {

            }
        });
    }

    /*
  get teacher's name
   */
    public void setTeacherName(int accountid, TextView tvName) {
        Call<ArrayList<Teacher>> call = messageRepository.getMessageService().getAllTeacher();
        call.enqueue(new Callback<ArrayList<Teacher>>() {
            @Override
            public void onResponse(Call<ArrayList<Teacher>> call, Response<ArrayList<Teacher>> response) {
                ArrayList<Teacher> teacherArrayList = new ArrayList<>();
                teacherArrayList= response.body();
                if(teacherArrayList!=null) {
                    for (int i = 0; i < teacherArrayList.size(); i++) {
                        if (teacherArrayList.get(i).getAccount().getAccountID() == accountid) {
                            tvName.setText(teacherArrayList.get(i).getName());
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Teacher>> call, Throwable t) {

            }
        });
    }

    public void setText(TextView tv, String text){
        tv.setText(text);
    }
}


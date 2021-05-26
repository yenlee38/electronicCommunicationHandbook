package com.example.electroniccommunicationhandbook.ui.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.database.RoomDB;
import com.example.electroniccommunicationhandbook.entity.Message;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class MainMessageAdapter extends RecyclerView.Adapter<MainMessageAdapter.ViewHolder>{
    private List<Message> messageList;
    private RoomDB db;
    public Context mcontext;
    //student list is used for searching function
    private List<Student> studentList;


    public MainMessageAdapter(List<Message> messageList, List<Student> studentList, Context context)
    {
        this.messageList = messageList;
        this.mcontext = context;
        this.studentList = studentList;
    }

    /*
    class holds structure of view
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ShapeableImageView personalImage;
        public TextView tvName;
        public TextView tvLastestMessage;
        public ConstraintLayout allBorder;

        public ViewHolder(View itemView){
            super(itemView);

            personalImage = (ShapeableImageView)itemView.findViewById(R.id.imgViewPersonalImage);
            tvName = (TextView)itemView.findViewById(R.id.tvUserName_Message);
            tvLastestMessage = (TextView)itemView.findViewById(R.id.tvLastestMessage);
            allBorder = (ConstraintLayout)itemView.findViewById(R.id.allBorder_message);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View messageView = layoutInflater.inflate(R.layout.item_in_main_message, parent, false);

        ViewHolder viewHolder = new ViewHolder(messageView);

        db = RoomDB.getDatabase(context);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if(messageList !=null)
            return messageList.size();
        else if (studentList!=null)
            return studentList.size();
        else
            return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //list recent message
        if (this.messageList!=null) {
            Message message = messageList.get(position);

//        holder.personalImage.setS
            int receiverID = message.getReceiverAccount().getAccountID();

            //get receiver's name
            String name = "";
            holder.tvName.setText("");
            db.databaseWriteExecutor.execute(() -> {
                Student std = db.studentDAO().getStudentByAccountID(receiverID);
                if (std != null)
                    ((MainMessage) mcontext).runOnUiThread(() -> {
                        holder.tvName.setText(std.getName());
                    });
                else {
                    Teacher teacher = db.teacherDAO().getTeacherByAccountID(receiverID);
                    if (teacher != null)
                        ((MainMessage) mcontext).runOnUiThread(() -> {
                            holder.tvName.setText(teacher.getName());
                        });
                }
            });

            holder.tvLastestMessage.setText(message.getContent());
            holder.tvLastestMessage.setVisibility(View.VISIBLE);
            holder.allBorder.setBackgroundResource(R.drawable.item_message_border);
        }
        else {  //list user (for searching user)
            Student student = this.studentList.get(position);

            holder.tvName.setText( student.getName());

            //invisible lastest message
            holder.tvLastestMessage.setVisibility(View.GONE);
            holder.allBorder.setBackgroundResource(R.drawable.no_border_message);

        }
    }
}

package com.example.electroniccommunicationhandbook.ui.message;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Message;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.repository.MessageRepository;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMessageAdapter extends RecyclerView.Adapter {
    //
    private final int MY_MESSAGE=1;
    private final int OTHER_MESSAGE=2;

    private ArrayList<Message> messageList;
    //myid is used to define the user using the app
    private int myID;
    private MessageRepository messageRepository;
    private Context mContext;
    int role;
    int otherID;

    public DetailMessageAdapter(ArrayList<Message> messageList, int myID, int otherID, MessageRepository messageRepository,
                                Context mContext, int role)
    {
        this.messageList = messageList;
        this.myID = myID;
        this.messageRepository = messageRepository;
        this.mContext = mContext;
        this.role = role;
        this.otherID=otherID;
    }

    public void setMessageList(ArrayList<Message> messageList) {
        this.messageList = messageList;
        notifyDataSetChanged();
    }

    /*
        class holds structure of each message
         */
    public static class MyDetailMessageViewHolder extends RecyclerView.ViewHolder{
        public TextView tvMyMessage;

        public MyDetailMessageViewHolder(View itemView){
            super(itemView);

            this.tvMyMessage = (TextView) itemView.findViewById(R.id.tvMyDetailMessage);
        }
    }

    /*
   class holds structure of each message
    */
    public static class OtherDetailMessageViewHolder extends RecyclerView.ViewHolder{
        public TextView tvOtherMessage;
        public ShapeableImageView image;

        public OtherDetailMessageViewHolder(View itemView){
            super(itemView);

            this.image = (ShapeableImageView)itemView.findViewById(R.id.imgViewOtherDetailMessage);
            this.tvOtherMessage = (TextView) itemView.findViewById(R.id.tv_other_detail_message);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case MY_MESSAGE:
                View view1 = layoutInflater.inflate(R.layout.item_my_detail_message, parent, false);
                return new MyDetailMessageViewHolder(view1);
            case OTHER_MESSAGE:
                View view2 = layoutInflater.inflate(R.layout.item_other_detail_message, parent, false);
                return new OtherDetailMessageViewHolder(view2);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messageList.get(position);

        switch (getItemViewType(position)) {
            case MY_MESSAGE:
                MyDetailMessageViewHolder myHolder = (MyDetailMessageViewHolder) holder;
                myHolder.tvMyMessage.setText(message.getContent());
                break;
            case OTHER_MESSAGE:
                OtherDetailMessageViewHolder otherHolder = (OtherDetailMessageViewHolder) holder;
                if(role==1){
                    setStudentImage(otherID, otherHolder.image, mContext);
                }
                else if (role==2){
                  setTeachertImage(otherID, otherHolder.image, mContext);
                }
                otherHolder.tvOtherMessage.setText(message.getContent());
                break;
            default:
                break;
        }
    }

    /*
    func: get type of view which is used to show
     */
    @Override
    public int getItemViewType(int position) {
        Message message = this.messageList.get(position);
        if(message.getSenderAccount().getAccountID()==this.myID)
            return MY_MESSAGE;
        else
            return OTHER_MESSAGE;
    }

    @Override
    public int getItemCount() {
        if(this.messageList == null)
            return 0;
        return this.messageList.size();
    }

    /*
  get student's image
   */
    public void setStudentImage(int accountid, ShapeableImageView imgView, Context context){
        MutableLiveData<ArrayList<Student>> studentMultableList = messageRepository.getAllStudents();

        studentMultableList.observeForever( new Observer<ArrayList<Student>>() {
            @Override
            public void onChanged(ArrayList<Student> stuList) {
                if(stuList !=null){
                    ArrayList<Student> stdList = new ArrayList<>();
                    stdList=stuList;
                    for (int i=0; i<stdList.size(); i++){
                        if(stdList.get(i).getAccount().getAccountID()==accountid){
                            try{
                                Glide.with(context).load(stdList.get(i).getImage())
                                        .error(R.drawable.cus_input)
                                        .into(imgView);
                            }
                            catch(Exception ex){
                                Log.e("Load image", "no image");
                            }
                            break;
                        }
                    }
                }
            }
        });
    }

    /*
get teacher's image
*/
    public void setTeachertImage(int accountid, ShapeableImageView imgView, Context mcontext ) {
        Call<ArrayList<Teacher>> call = messageRepository.getMessageService().getAllTeacher();
        call.enqueue(new Callback<ArrayList<Teacher>>() {
            @Override
            public void onResponse(Call<ArrayList<Teacher>> call, Response<ArrayList<Teacher>> response) {
                ArrayList<Teacher> teacherArrayList = new ArrayList<>();
                teacherArrayList= response.body();
                if(teacherArrayList!=null) {
                    for (int i = 0; i < teacherArrayList.size(); i++) {
                        if (teacherArrayList.get(i).getAccount().getAccountID() == accountid) {
                            try{
                                Glide.with(mcontext).load(teacherArrayList.get(i).getImage())
                                        .error(R.drawable.cus_input)
                                        .into(imgView);
                            }
                            catch(Exception ex){
                                Log.e("Load image", "no image");
                            }

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
}

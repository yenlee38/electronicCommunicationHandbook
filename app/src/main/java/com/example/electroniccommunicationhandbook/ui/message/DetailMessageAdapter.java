package com.example.electroniccommunicationhandbook.ui.message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Message;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class DetailMessageAdapter extends RecyclerView.Adapter {
    //
    private final int MY_MESSAGE=1;
    private final int OTHER_MESSAGE=2;

    private List<Message> messageList;
    //myid is used to define the user using the app
    private int myID;

    public DetailMessageAdapter(List<Message> messageList, int myID)
    {
        this.messageList = messageList;
        this.myID = myID;
    }

    /*
    class holds structure of each message
     */
    public static class MyDetailMessageViewHolder extends RecyclerView.ViewHolder{
        public TextView tvMyMessage;

        public MyDetailMessageViewHolder(View itemView){
            super(itemView);

//            this.tvMyMessage = (TextView) itemView.findViewById(R.id.tvMyDetailMessage);
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

//            this.image = (ShapeableImageView)itemView.findViewById(R.id.imgViewOtherDetailMessage);
//            this.tvOtherMessage = (TextView) itemView.findViewById(R.id.tv_other_detail_message);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        /*switch (viewType){
            case MY_MESSAGE:
                View view1 = layoutInflater.inflate(R.layout.item_my_detail_message, parent, false);
                return new MyDetailMessageViewHolder(view1);
            case OTHER_MESSAGE:
                View view2 = layoutInflater.inflate(R.layout.item_other_detail_message, parent, false);
                return new OtherDetailMessageViewHolder(view2);
            default:
                return null;
        }*/
        return null;
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
                //otherHolder.image
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
//        if(message.getSenderAccountID()==this.myID)
//            return MY_MESSAGE;
//        else
//            return OTHER_MESSAGE;
        return 0;
    }

    @Override
    public int getItemCount() {
        if(this.messageList == null)
            return 0;
        return this.messageList.size();
    }
}

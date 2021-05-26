package com.example.electroniccommunicationhandbook.ui.message;

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
    private List<Message> messageList;
    //myid is used to define the user using the app
    private String myID;

    public DetailMessageAdapter(List<Message> messageList, String myID)
    {
        this.messageList = messageList;
        this.myID = myID;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
        public TextView tvMyMessage;
        public ShapeableImageView image;

        public OtherDetailMessageViewHolder(View itemView){
            super(itemView);

            this.image = (ShapeableImageView)itemView.findViewById(R.id.imgViewOtherDetailMessage);
            this.tvMyMessage = (TextView) itemView.findViewById(R.id.tvMyDetailMessage);
        }
    }



}

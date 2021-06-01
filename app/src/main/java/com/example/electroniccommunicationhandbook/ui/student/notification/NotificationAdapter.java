package com.example.electroniccommunicationhandbook.ui.student.notification;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Announcement;
import com.example.electroniccommunicationhandbook.ui.student.point.PointAdapter;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private ArrayList<Announcement> announcementList;
    private  Context context;

    public NotificationAdapter(ArrayList<Announcement> announcements, Context context) {
        announcementList= new ArrayList<>();
        this.announcementList = announcements;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View recyclerView = inflater.inflate(R.layout.item_notification, parent, false);

        ViewHolder viewHolder= new ViewHolder(recyclerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Announcement announcement= announcementList.get(position);
        holder.tvContent.setText(announcement.getContent());
        holder.tvTeacher.setText(announcement.getSender().getName());
        holder.tvTimeCreate.setText(String.valueOf(announcement.getAnnouncementTime()));
        holder.tvTitle.setText(String.valueOf(announcement.getAnnouncementTime()).substring(0,10));
    }

    @Override
    public int getItemCount() {
        return announcementList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTeacher;
        private TextView tvTitle;
        private  TextView tvContent;
        private TextView tvTimeCreate;
        private View image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTeacher= itemView.findViewById(R.id.tv_teacher);
            tvContent= itemView.findViewById(R.id.tv_content);
            tvTimeCreate= itemView.findViewById(R.id.tv_timeCreate);
            tvTitle= itemView.findViewById(R.id.tv_title);
            image= itemView.findViewById(R.id.img_teacher);
        }
    }
}

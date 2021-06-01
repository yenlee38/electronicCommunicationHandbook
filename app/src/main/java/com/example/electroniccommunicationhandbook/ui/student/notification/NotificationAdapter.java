package com.example.electroniccommunicationhandbook.ui.student.notification;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Announcement;
import com.example.electroniccommunicationhandbook.ui.student.point.PointAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
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
        if(announcement!=null){
            holder.tvContent.setText(announcement.getContent());
            if(announcement.getSender()==null)
                holder.tvTeacher.setText("SCHOOL ");
            else
                holder.tvTeacher.setText(announcement.getSender().getName());
            holder.tvTimeCreate.setText(String.valueOf(announcement.getAnnouncementTime()));
            holder.tvTitle.setText(announcement.getTitle());
            holder.tvClass.setText("Class: "+ announcement.get_class().getSubject().getName());
//            if(announcement.getSender().getImage()!=null)
//            {
//                try {
//                    String imageUrl= announcement.getSender().getImage();
//                    Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageUrl).getContent());
//                    holder.image.setImageBitmap(bitmap);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }

        }

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
        private TextView tvClass;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTeacher= itemView.findViewById(R.id.tv_teacher);
            tvContent= itemView.findViewById(R.id.tv_content);
            tvTimeCreate= itemView.findViewById(R.id.tv_timeCreate);
            tvTitle= itemView.findViewById(R.id.tv_title);
            image= itemView.findViewById(R.id.img_teacher);
            tvClass= itemView.findViewById(R.id.tvClass);
        }
    }
}

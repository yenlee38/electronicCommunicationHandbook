package com.example.electroniccommunicationhandbook.ui.student.point;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Point;

import java.util.ArrayList;
import java.util.List;

public class PointAdapter extends RecyclerView.Adapter<PointAdapter.ViewHolder> {


    ArrayList<Point>  pointList;
    Context context;

    public PointAdapter(ArrayList<Point> pointList, Context context) {
        this.pointList= new ArrayList<>();
        this.pointList = pointList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View recyclerView = inflater.inflate(R.layout.item_point_view, parent, false);

        ViewHolder viewHolder= new ViewHolder(recyclerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Point point =  pointList.get(position);

        holder.tvsubject.setText(point.getSubject());
        holder.tvmidtermpoint.setText(String.valueOf(point.getMidterm()));
        holder.tvfinalpoint.setText(String.valueOf(point.getFinalterm()));
        holder.tvresult.setText(String.valueOf(point.getTotal()));
        holder.tvgrade.setText(String.valueOf(point.getGrade()));
        if(point.isIspass())
        {
            holder.tvpass.setText("Pass");
            holder.imgPass.setImageResource(R.drawable.ic_baseline_check_24);

        }
        else
        {
            holder.tvpass.setText("Fail");
            holder.tvpass.setTextColor(Color.rgb(222,14,9));
            holder.border.setBackgroundResource(R.drawable.border_point_item1);
            holder.pass.setBackgroundColor(Color.rgb(255,188,220));
            holder.imgPass.setImageResource(R.drawable.ic_baseline_close_24);
        }

    }

    @Override
    public int getItemCount() {
        return pointList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvsubject, tvmidtermpoint, tvfinalpoint, tvgrade, tvpass, tvresult;
        private View itemView;
        private ImageView imgPass;
        private ConstraintLayout border;
        private LinearLayout pass;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView= itemView;
            tvfinalpoint = this.itemView.findViewById(R.id.tv_final_point);
            tvgrade= this.itemView.findViewById(R.id.tv_grade);
            tvmidtermpoint = this.itemView.findViewById(R.id.tv_midterm_point);
            tvpass= this.itemView.findViewById(R.id.tv_pass);
            tvsubject= this.itemView.findViewById(R.id.tv_subject_name);
            tvresult= this.itemView.findViewById(R.id.tv_result_point);
            imgPass= this.itemView.findViewById(R.id.img_pass);
            border =this.itemView.findViewById(R.id.borderpoint);
            pass = this.itemView.findViewById(R.id.linearLayoutPoint);
        }
    }
}



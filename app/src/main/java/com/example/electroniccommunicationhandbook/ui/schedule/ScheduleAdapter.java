package com.example.electroniccommunicationhandbook.ui.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.SchoolTime;
import com.example.electroniccommunicationhandbook.ui.student.rate.RateTeacherAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>{

    private ArrayList<Class> mClassOfDay;
    public ScheduleAdapter(ArrayList<Class> mClassOfDay) {
        this.mClassOfDay = mClassOfDay;
    }

    public ArrayList<Class> getmClassOfDay() {
        return mClassOfDay;
    }

    private ArrayList<SchoolTime> lSchoolTime;

    public void setlSchoolTime(ArrayList<SchoolTime> lSchoolTime) {
        this.lSchoolTime = lSchoolTime;
    }

    public  ScheduleAdapter(){}

    public void setmClassOfDay(ArrayList<Class> mClassOfDay) {
        this.mClassOfDay = mClassOfDay;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_chedule,parent, false);
        return new ScheduleAdapter.ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        if(mClassOfDay.get(position).getSubject().getName() != null){
            holder.getTv_subject_name().setText(mClassOfDay.get(position).getSubject().getName());
        }

        try{
            if(mClassOfDay.get(position).getStartSchoolTime() != 0){
                holder.getTv_start_room().setText(getTime(mClassOfDay.get(position).getStartSchoolTime()).getStartingTime());
            }

            if(mClassOfDay.get(position).getEndSchoolTime() != 0){
                holder.getTv_end_room().setText(getTime(mClassOfDay.get(position).getEndSchoolTime()).getEndTime());
            }
        }catch (Exception ex){}

        if(mClassOfDay.get(position).getClassRoom() != null){
            holder.getTv_room_name().setText(mClassOfDay.get(position).getClassRoom());
        }


    }

    private SchoolTime getTime(int time){
        for(int i = 0; i < lSchoolTime.size(); i++)
            if(lSchoolTime.get(i).getSchoolTimeOrder() == time)
                return lSchoolTime.get(i);
            return null;
    }

    @Override
    public int getItemCount() {
        return mClassOfDay.size();
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_subject_name;
        private TextView tv_room_name;
        private TextView tv_start_room;
        private TextView tv_end_room;

        public ScheduleViewHolder(@NonNull View itemView) {

            super(itemView);
            tv_subject_name = itemView.findViewById(R.id.tv_subject_name);
            tv_room_name = itemView.findViewById(R.id.tv_room_name);
            tv_start_room = itemView.findViewById(R.id.tv_start_room);
            tv_end_room = itemView.findViewById(R.id.tv_end_room);
        }

        public TextView getTv_subject_name() {
            return tv_subject_name;
        }

        public void setTv_subject_name(TextView tv_subject_name) {
            this.tv_subject_name = tv_subject_name;
        }

        public TextView getTv_room_name() {
            return tv_room_name;
        }

        public void setTv_room_name(TextView tv_room_name) {
            this.tv_room_name = tv_room_name;
        }

        public TextView getTv_start_room() {
            return tv_start_room;
        }

        public void setTv_start_room(TextView tv_start_room) {
            this.tv_start_room = tv_start_room;
        }

        public TextView getTv_end_room() {
            return tv_end_room;
        }

        public void setTv_end_room(TextView tv_end_room) {
            this.tv_end_room = tv_end_room;
        }
    }
}

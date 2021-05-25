package com.example.electroniccommunicationhandbook.ui.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.ui.student.rate.RateTeacherAdapter;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>{

    private List<Class> mClassOfDay;
    public ScheduleAdapter(List<Class> mClassOfDay) {
        this.mClassOfDay = mClassOfDay;
    }

    public List<Class> getmClassOfDay() {
        return mClassOfDay;
    }

    public  ScheduleAdapter(){}

    public void setmClassOfDay(List<Class> mClassOfDay) {
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

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {


        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

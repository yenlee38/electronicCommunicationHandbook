package com.example.electroniccommunicationhandbook.ui.student.rate;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Student_Class;

import java.util.ArrayList;

public class RateTeacherAdapter extends RecyclerView.Adapter<RateTeacherAdapter.RateTeacherViewHolder> {


    private ArrayList<Student_Class> mClass;

    public RateTeacherAdapter (ArrayList<Student_Class> mClass){
        this.mClass = mClass;
    }

    public RateTeacherAdapter() {

    }

    @NonNull
    @Override
    public RateTeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rate_teacher,parent, false);
        return new RateTeacherAdapter.RateTeacherViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RateTeacherViewHolder holder, int position) {


        try {

            holder.getTv_name_teacher_rate().setText(mClass.get(position).get_class().getTeacher().getName());
            holder.getTv_name_subject_rate().setText(mClass.get(position).get_class().getSubject().getName());
            int rate = mClass.get(position).getRating();
            holder.setChangeBtnRate(rate, holder.itemView);

        }catch (Exception ex){}


        holder.getBtn_rate().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Bundle bundle = new Bundle();
                bundle.putSerializable("student_class_object", mClass.get(position));
                RateDetailFragment myFragment = new RateDetailFragment();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.layout_rate_teacher, myFragment).addToBackStack(null).commit();


            }
        });
    }

    @Override
    public int getItemCount() {
        return mClass.size();
    }

    public class RateTeacherViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name_teacher_rate;
        private TextView tv_name_subject_rate;
        private TextView tv_status_rate;
        private AppCompatButton btn_rate;
        private ImageView img_avatar;

        public RateTeacherViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name_teacher_rate = itemView.findViewById(R.id.tv_name);
            tv_name_subject_rate = itemView.findViewById(R.id.tv_name_subject_rate);
            tv_status_rate = itemView.findViewById(R.id.tv_status_rate);
            btn_rate = itemView.findViewById(R.id.btn_rate);
            img_avatar = itemView.findViewById(R.id.img_avatar);
        }

        public void setTv_name_teacher_rate(TextView tv_name_teacher_rate) {
            this.tv_name_teacher_rate = tv_name_teacher_rate;
        }

        public void setTv_name_subject_rate(TextView tv_name_subject_rate) {
            this.tv_name_subject_rate = tv_name_subject_rate;
        }

        public void setTv_status_rate(TextView tv_status_rate) {
            this.tv_status_rate = tv_status_rate;
        }

        public void setBtn_rate(AppCompatButton btn_rate) {
            this.btn_rate = btn_rate;
        }

        public void setImg_avatar(ImageView img_avatar) {
            this.img_avatar = img_avatar;
        }

        public TextView getTv_name_teacher_rate() {
            return tv_name_teacher_rate;
        }

        public TextView getTv_name_subject_rate() {
            return tv_name_subject_rate;
        }

        public TextView getTv_status_rate() {
            return tv_status_rate;
        }

        public AppCompatButton getBtn_rate() {
            return btn_rate;
        }

        public ImageView getImg_avatar() {
            return img_avatar;
        }

        public void setChangeBtnRate(int rate, View v){
            AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
            if(rate == 0)
            {
                getTv_status_rate().setText(activity.getString(R.string.not_yet_rated));
                getBtn_rate().setBackground(activity.getDrawable(R.drawable.border_btn_rate));
                getTv_status_rate().setTextColor(activity.getColor(R.color.color_text_status_not_rate));
            }
            else{
                getTv_status_rate().setText(activity.getString(R.string.rated));
                getBtn_rate().setBackground(activity.getDrawable(R.drawable.border_btn_not_rate));
                getTv_status_rate().setTextColor(activity.getColor(R.color.color_text_status_rate));

            }
        }
    }
}

package com.example.electroniccommunicationhandbook.ui.student.rate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.ui.teacher.list_class.ListClassAdapter;

import java.util.List;

public class RateTeacherAdapter extends RecyclerView.Adapter<RateTeacherAdapter.RateTeacherViewHolder> {


    private List<Class> mClass;

    public RateTeacherAdapter (List<Class> mClass){
        this.mClass = mClass;
        Class c = new Class();
        c.setClassId("C01");
    }

    @NonNull
    @Override
    public RateTeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rate_teacher,parent, false);
        return new RateTeacherAdapter.RateTeacherViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RateTeacherViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() { // call fragment
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment fragment = new RateDetailFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, fragment)
                        .addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class RateTeacherViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name_teacher_rate;
        private TextView tv_name_subject_rate;
        private TextView tv_status_rate;
        private AppCompatButton btn_rate;

        public RateTeacherViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name_teacher_rate = itemView.findViewById(R.id.tv_name);
            tv_name_subject_rate = itemView.findViewById(R.id.tv_name_subject_rate);
            tv_status_rate = itemView.findViewById(R.id.tv_status_rate);
            btn_rate = itemView.findViewById(R.id.btn_rate);
        }
    }
}

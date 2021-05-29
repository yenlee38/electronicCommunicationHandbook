package com.example.electroniccommunicationhandbook.ui.teacher.list_class;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Student;

import java.util.List;

public class ListStudentsInClassAdapter extends RecyclerView.Adapter<ListStudentsInClassAdapter.StudentsViewHolder> {
    List<Student> data;

    public ListStudentsInClassAdapter(List<Student> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_students,parent, false);

        return new  StudentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {
        holder.tvStudentName.setText(data.get(position).getName());
        holder.tvStudentId.setText(data.get(position).getStudentId());
    }

    @Override
    public int getItemCount() {
        if(!data.isEmpty()){
            return data.size();
        }
        return 0;
    }

    public static class StudentsViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName;
        TextView tvStudentId;


        public StudentsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentName= itemView.findViewById(R.id.tv_item_class_numbers);
            tvStudentId= itemView.findViewById(R.id.tv_item_class_student);
        }
    }
}

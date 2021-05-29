package com.example.electroniccommunicationhandbook.ui.teacher.list_class;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.entity.Subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListClassAdapter extends RecyclerView.Adapter<ListClassAdapter.ClassesViewHolder> {
    ArrayList<Class> data;

    public ListClassAdapter(ArrayList<Class> listClass) {
        this.data = listClass;
    }

    @NonNull
    @Override
    public ListClassAdapter.ClassesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_class, parent, false);
        return new ClassesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassesViewHolder holder, int position) {
        //  holder.numberOfClass.setText(data.getClass().);
//        if (!data.get(position).getStudents().isEmpty()){
//            holder.listStudentInClass.addAll(data.get(position).getStudents());
//
//        }
        holder.nameSubject.setText(data.get(position).getSubject().getName());
        //  holder.numberOfClass.setText(data.get(position).getStudents().size());
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        return 0;
    }

    public static class ClassesViewHolder extends RecyclerView.ViewHolder {
        private TextView nameSubject;
        private TextView numberOfClass;
        private AppCompatButton btnViewStudents;
        private ArrayList<Student_Class> listStudentInClass;

        public ClassesViewHolder(@NonNull View itemView) {
            super(itemView);
            nameSubject = itemView.findViewById(R.id.tv_item_class_subject);
            numberOfClass = itemView.findViewById(R.id.tv_item_class_numbers);
            btnViewStudents = itemView.findViewById(R.id.btn_class_view_student);
            listStudentInClass = new ArrayList<>();
            btnViewStudents.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!listStudentInClass.isEmpty()) {
                        Intent intent = new Intent(itemView.getContext(), StudentInClass.class);
                        intent.putExtra("StudentInClass", (Serializable) listStudentInClass);

                        itemView.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}

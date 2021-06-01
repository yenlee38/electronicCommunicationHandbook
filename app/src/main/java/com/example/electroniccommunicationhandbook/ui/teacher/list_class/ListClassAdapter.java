package com.example.electroniccommunicationhandbook.ui.teacher.list_class;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import java.io.Serializable;
import java.util.ArrayList;

public class ListClassAdapter extends RecyclerView.Adapter<ListClassAdapter.ClassesViewHolder> {
    ArrayList<Class> data;
    Context mContext;
    public ListClassAdapter(ArrayList<Class> listClass, Context mContext) {
        this.mContext= mContext;
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
        holder.classId= data.get(position).getClassId();
        holder.credit.setText(data.get(position).getClassRoom());
        holder.nameSubject.setText(data.get(position).getSubject().getName());
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        return 0;
    }

    public class ClassesViewHolder extends RecyclerView.ViewHolder {
        private TextView nameSubject;
        private TextView numberOfClass;
        private TextView credit;
        private AppCompatButton btnViewStudents;
        String   classId;

        public ClassesViewHolder(@NonNull View itemView) {
            super(itemView);
            nameSubject = itemView.findViewById(R.id.tv_item_class_subject);
            numberOfClass = itemView.findViewById(R.id.tv_item_class_numbers);
            btnViewStudents = itemView.findViewById(R.id.btn_class_view_student);
            credit=itemView.findViewById(R.id.tv_item_class_credit);
            btnViewStudents.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, StudentInClass.class);
                    intent.putExtra("classId", classId);

                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}

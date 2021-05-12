package com.example.electroniccommunicationhandbook.ui.teacher.list_class;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Subject;

import java.util.List;

public class ListClassAdapter extends RecyclerView.Adapter<ListClassAdapter.ClassesViewHolder> {
    List<Subject> listSubject;
    Context mContext;
    public ListClassAdapter(Context mContext,List<Subject> data){
        this.listSubject=data;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public ListClassAdapter.ClassesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_class,parent, false);
        return new ClassesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassesViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return listSubject.size();
    }

    public static class ClassesViewHolder extends RecyclerView.ViewHolder{
        private TextView nameSubject;
        private TextView numberOfClass;

        public ClassesViewHolder(@NonNull View itemView) {
            super(itemView);
            nameSubject= itemView.findViewById(R.id.tv_item_class_subject);
            numberOfClass= itemView.findViewById(R.id.tv_item_class_numbers);
        }
    }
}

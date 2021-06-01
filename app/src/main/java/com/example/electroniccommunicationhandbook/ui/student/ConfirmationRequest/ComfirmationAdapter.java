package com.example.electroniccommunicationhandbook.ui.student.ConfirmationRequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_ConfirmationPaper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComfirmationAdapter extends RecyclerView.Adapter<ComfirmationAdapter.ViewHolder> {

    private ArrayList<Student_ConfirmationPaper> data;

    public ComfirmationAdapter(ArrayList<Student_ConfirmationPaper> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitleRequest.setText(data.get(position).getConfirmationPaper().getConfirmationPaperName());

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateTypeAdapter()).create();

        holder.tvDateRequest.setText(gson.toJson(data.get(position).getRequiredTime().toString()));
       // holder.tvDateRequest.setText(data.get(position).getRequiredTime().toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitleRequest, tvDateRequest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitleRequest = itemView.findViewById(R.id.tvTitleRequest);
            tvDateRequest = itemView.findViewById(R.id.tvDateRequest);
        }
    }
}

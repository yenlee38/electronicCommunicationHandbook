package com.example.electroniccommunicationhandbook.ui.teacher.list_class;

import android.app.Dialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Student;

import java.util.ArrayList;
import java.util.List;

import static com.example.electroniccommunicationhandbook.util.Comon.VIEW_CODE;

public class ListStudentsInClassAdapter extends RecyclerView.Adapter<ListStudentsInClassAdapter.StudentsViewHolder> {
    ArrayList<Student> data;

    public ListStudentsInClassAdapter(ArrayList<Student> data) {
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
        holder.tvAddress=data.get(position).getAddress();
        holder.tvPhone=data.get(position).getPhone();
        holder.tvEmail=data.get(position).getPhone();
    }

    @Override
    public int getItemCount() {
        if(!data.isEmpty()){
            return data.size();
        }
        return 0;
    }

    public static class StudentsViewHolder extends RecyclerView.ViewHolder  {
        TextView tvStudentName;
        TextView tvStudentId;
        String tvPhone;
        String tvAddress;
        String tvEmail;
        public StudentsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentName= itemView.findViewById(R.id.tv_item_class_numbers);
            tvStudentId= itemView.findViewById(R.id.tv_item_class_student);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Dialog dialog= new Dialog(v.getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.dialog_detail_student);

                    EditText name= dialog.findViewById(R.id.edit_detail_name);
                    EditText email= dialog.findViewById(R.id.edit_detail_email);
                    EditText phone= dialog.findViewById(R.id.edit_detail_phone);
                    EditText address= dialog.findViewById(R.id.edit_detail_address);
                    EditText studentId= dialog.findViewById(R.id.edit_detail_id);

                    name.setText(tvStudentName.getText());
                    email.setText(tvEmail);
                    phone.setText(tvPhone);
                    address.setText(tvAddress);
                    studentId.setText(tvStudentId.getText());


                    Button btnCancel= dialog.findViewById(R.id.btn_detail_cancel);
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    return false;
                }
            });
        }


    }
}

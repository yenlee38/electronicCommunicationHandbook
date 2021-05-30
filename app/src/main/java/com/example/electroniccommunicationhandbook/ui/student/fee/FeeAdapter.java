package com.example.electroniccommunicationhandbook.ui.student.fee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.FeeInfor;

import java.util.ArrayList;

public class FeeAdapter extends RecyclerView.Adapter<FeeAdapter.ViewHolder> {

    private ArrayList<FeeInfor> feeInfors;

    public FeeAdapter(ArrayList<FeeInfor> feeInfors) {
        this.feeInfors= new ArrayList<>();
        this.feeInfors = feeInfors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View recyclerView = inflater.inflate(R.layout.item_fee_view, parent, false);
        ViewHolder viewHolder= new ViewHolder(recyclerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        FeeInfor feeInfor= new FeeInfor();
        feeInfor= feeInfors.get(position);
        holder.tvSubjectCredit.setText(feeInfor.getNumberCredit()+"TC");
       // holder.tvSubjectFee.setText(String.valueOf( fee.getFee()));
        holder.tvSubjectname.setText(feeInfor.getSubjectName());
        holder.tvSubjectFee.setText(feeInfor.getTotalFee()+" VND");

    }

    @Override
    public int getItemCount() {
        return feeInfors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvSubjectname, tvSubjectCredit, tvSubjectFee;
        private View itemview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview= itemView;
            tvSubjectCredit = itemview.findViewById(R.id.tv_noCredit);
            tvSubjectname= itemview.findViewById(R.id.tv_subject_name);
            tvSubjectFee = itemview.findViewById(R.id.tv_subject_fee);
        }
    }


}

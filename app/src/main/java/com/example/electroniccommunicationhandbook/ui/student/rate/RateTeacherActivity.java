package com.example.electroniccommunicationhandbook.ui.student.rate;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Class;

import java.util.List;

public class RateTeacherActivity extends AppCompatActivity {

    private RateTeacherAdapter rateTeacherAdapter;
    private List<Class> mClass = null;
    private RecyclerView rlv_rateTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_teacher);
        initView();
        setRecyclerView();

    }

    private void setRecyclerView(){ //
        rateTeacherAdapter = new RateTeacherAdapter(mClass);
        rlv_rateTeacher.setAdapter(rateTeacherAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rlv_rateTeacher.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void initView(){
        rlv_rateTeacher = findViewById(R.id.rlv_rateTeacher);
    }

}

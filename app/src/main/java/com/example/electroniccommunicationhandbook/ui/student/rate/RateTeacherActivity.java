package com.example.electroniccommunicationhandbook.ui.student.rate;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.repository.StudentRepository;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import java.util.List;

public class RateTeacherActivity extends AppCompatActivity {

    private RateTeacherAdapter rateTeacherAdapter;
    private List<Class> lClass = null;
    private RecyclerView rlv_rateTeacher;
    private ImageView img_back;
    UserLocalStore userLocalStore;
    private Student student;
    private StudentRepository studentRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_teacher);
        userLocalStore = new UserLocalStore(getApplicationContext());
        student = userLocalStore.getStudentLocal();
        initView();
        setRecyclerView();

    }

    private void setRecyclerView(){ //
        rateTeacherAdapter = new RateTeacherAdapter(lClass);
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
        img_back = findViewById(R.id.img_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}

package com.example.electroniccommunicationhandbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;



import com.example.electroniccommunicationhandbook.dao.StudentDAO;
import com.example.electroniccommunicationhandbook.repository.StudentRepository;
import com.example.electroniccommunicationhandbook.service.StudentService;
import com.example.electroniccommunicationhandbook.ui.schedule.ScheduleActivity;
import com.example.electroniccommunicationhandbook.ui.profile.ProfileActivity;


import com.example.electroniccommunicationhandbook.ui.profile.ProfileActivity;

import com.example.electroniccommunicationhandbook.ui.student.card.CardActivity;
import com.example.electroniccommunicationhandbook.ui.student.point.PointViewActivity;
import com.example.electroniccommunicationhandbook.ui.student.rate.RateTeacherActivity;


import android.widget.Button;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.repository.PointRepository;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;
    //MainRepository mainRepository;
    PointRepository pointService;
    TextView tvName;


    StudentRepository studentRepository;
    private AppCompatButton btn_profile;


    private AppCompatButton btn_student_card;
    private AppCompatButton btn_rate;
    private AppCompatButton btn_schedule;

    private AppCompatButton btn_logout;

    private  AppCompatButton btn_point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView(){
        btn_profile = findViewById(R.id.btn_profile);
        btn_student_card = findViewById(R.id.btn_student_card);
        btn_rate = findViewById(R.id.btn_rate);
        btn_schedule = findViewById(R.id.btn_schedule);
        btn_point= findViewById(R.id.btn_points);

        btn_student_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CardActivity.class);
                startActivity(intent);
              //  setContentView(R.layout.activity_card_student);

            }
        });

        btn_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RateTeacherActivity.class);
                startActivity(intent);
                //setContentView(R.layout.activity_rate_teacher);

            }
        });

        btn_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                startActivity(intent);
               // setContentView(R.layout.activity_schedule);

            }
        });



        btn_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PointViewActivity.class);
            } });


        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
    }


}
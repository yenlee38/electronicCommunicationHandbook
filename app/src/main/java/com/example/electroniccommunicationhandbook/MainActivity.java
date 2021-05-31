package com.example.electroniccommunicationhandbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;


import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.repository.StudentRepository;
import com.example.electroniccommunicationhandbook.ui.message.MainMessage;
import com.example.electroniccommunicationhandbook.ui.schedule.ScheduleActivity;

import com.example.electroniccommunicationhandbook.ui.student.ConfirmationRequest.ConfirmationRequestActivity;

import com.example.electroniccommunicationhandbook.ui.profile.ProfileActivity;


import com.example.electroniccommunicationhandbook.ui.student.card.CardActivity;
import com.example.electroniccommunicationhandbook.ui.student.fee.FeeViewActivity;
import com.example.electroniccommunicationhandbook.ui.student.notification.NotificationActivity;
import com.example.electroniccommunicationhandbook.ui.student.point.PointViewActivity;
import com.example.electroniccommunicationhandbook.ui.student.rate.RateTeacherActivity;


import android.widget.Button;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.repository.PointRepository;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import java.time.Year;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;
    //MainRepository mainRepository;
    PointRepository pointService;
    TextView tvName;


    StudentRepository studentRepository;

    private Button btnMessage;

    private AppCompatButton btn_profile;

    private AppCompatButton btn_student_card;
    private AppCompatButton btn_rate;
    private AppCompatButton btn_schedule;
    private AppCompatButton btn_request;
    private AppCompatButton btn_notification;
    private AppCompatButton btn_fee;
    private AppCompatButton btn_logout;

    private AppCompatButton btn_point;
    private TextView tv_username;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        tvName = findViewById(R.id.txvName);
        btnLogout = findViewById(R.id.hoang);
        btn_request = findViewById(R.id.btn_request);
        pointService = PointRepository.getInstance(getApplication());
        userLocalStore = new UserLocalStore(getApplicationContext());
        Student student = new Student();
       try{ student = userLocalStore.getStudentLocal();
       tv_username.setText(student.getName());}
       catch (Exception ex){}
    }


    private void initView() {
        btn_profile = findViewById(R.id.btn_profile);
        btn_student_card = findViewById(R.id.btn_student_card);
        btn_rate = findViewById(R.id.btn_rate);
        btn_schedule = findViewById(R.id.btn_schedule);
        btn_point = findViewById(R.id.btn_points);
        btnMessage = findViewById(R.id.btn_message);
        btn_request= findViewById(R.id.btn_request);
        btn_notification= findViewById(R.id.btn_notification);
        btn_fee= findViewById(R.id.btn_fee);
        tv_username = findViewById(R.id.tv_username);


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
                RateTeacherActivity rateTeacherActivity = new RateTeacherActivity();
                rateTeacherActivity.YEAR = Year.now().getValue();
                if(Calendar.getInstance().MONTH < 9)
                    rateTeacherActivity.SEMESTER = 2;
                else rateTeacherActivity.SEMESTER = 1;
                Intent intent = new Intent(getApplicationContext(), rateTeacherActivity.getClass());
                startActivity(intent);
                //setContentView(R.layout.activity_rate_teacher);

            }
        });

        btn_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScheduleActivity scheduleActivity = new ScheduleActivity();
                scheduleActivity.YEAR = Year.now().getValue();
                scheduleActivity.DAY = Calendar.getInstance().DAY_OF_WEEK;
                if(Calendar.getInstance().MONTH < 9)
                    scheduleActivity.SEMESTER = 2;
                else scheduleActivity.SEMESTER = 1;
                Intent intent = new Intent(getApplicationContext(), scheduleActivity.getClass());
                startActivity(intent);
                // setContentView(R.layout.activity_schedule);

            }
        });


        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConfirmationRequestActivity.class);
                startActivity(intent);
            }
        });

        btn_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PointViewActivity.class);
                startActivity(intent);
            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);

                startActivity(intent);
            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMessage.class);
                startActivity(intent);
                // setContentView(R.layout.activity_schedule);

            }
        });

        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });

         btn_fee.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent= new Intent(getApplicationContext(), FeeViewActivity.class);
                 startActivity(intent);
             }
         });
    }
}

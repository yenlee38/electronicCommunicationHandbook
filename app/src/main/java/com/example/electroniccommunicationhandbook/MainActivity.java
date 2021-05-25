package com.example.electroniccommunicationhandbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;


import com.example.electroniccommunicationhandbook.ui.profile.ProfileActivity;
import com.example.electroniccommunicationhandbook.ui.student.card.CardActivity;
import com.example.electroniccommunicationhandbook.ui.student.rate.RateTeacherActivity;


import android.widget.Button;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.repository.MainRepository;
import com.example.electroniccommunicationhandbook.repository.PointRepository;
import com.example.electroniccommunicationhandbook.service.PointService;

import java.util.List;

import retrofit2.Call;

import static com.example.electroniccommunicationhandbook.util.Comon.MY_PREFS_FILE;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;
    //MainRepository mainRepository;
    PointRepository pointService;
    TextView tvName;
    private AppCompatButton btn_profile;
    private AppCompatButton btn_student_card;
    private AppCompatButton btn_rate;
    private AppCompatButton btn_schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        tvName= findViewById(R.id.txvName);
        btnLogout= findViewById(R.id.hoang);
        pointService=  PointRepository.getInstance();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //  mainRepository = MainRepository.getInstance();
                Student_Class student_class = new Student_Class();
                Class mclass = new Class();
                Student student = new Student();
                mclass.setClassId("Class1");

                student_class.set_class(mclass);
                student_class.setStudent(student);
                student = pointService.createPointInfo("1");
                if (student != null)
                    tvName.setText(student.getName() + " " + student.getBirthday());
                else
                    tvName.setText("Null roi");
            }});
    }

    private void initView(){
        btn_profile = findViewById(R.id.btn_profile);
        btn_student_card = findViewById(R.id.btn_student_card);
        btn_rate = findViewById(R.id.btn_rate);
        btn_schedule = findViewById(R.id.btn_schedule);

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
                Intent intent = new Intent(getApplicationContext(), RateTeacherActivity.class);
                startActivity(intent);
               // setContentView(R.layout.activity_schedule);

            }
        });
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
    }


}
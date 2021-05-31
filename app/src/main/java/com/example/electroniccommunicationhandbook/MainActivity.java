package com.example.electroniccommunicationhandbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;


import com.example.electroniccommunicationhandbook.dao.StudentDAO;
import com.example.electroniccommunicationhandbook.repository.StudentRepository;
import com.example.electroniccommunicationhandbook.service.StudentService;
import com.example.electroniccommunicationhandbook.ui.authentication.login.Login;
import com.example.electroniccommunicationhandbook.ui.message.MainMessage;
import com.example.electroniccommunicationhandbook.ui.schedule.ScheduleActivity;

import com.example.electroniccommunicationhandbook.ui.student.ConfirmationRequest.ConfirmationRequestActivity;

import com.example.electroniccommunicationhandbook.ui.profile.ProfileActivity;


import com.example.electroniccommunicationhandbook.ui.student.card.CardActivity;
import com.example.electroniccommunicationhandbook.ui.student.point.PointViewActivity;
import com.example.electroniccommunicationhandbook.ui.student.rate.RateTeacherActivity;


import android.widget.Button;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.repository.PointRepository;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.repository.MainRepository;
import com.example.electroniccommunicationhandbook.repository.PointRepository;
import com.example.electroniccommunicationhandbook.service.PointService;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import java.util.List;

import retrofit2.Call;

import static com.example.electroniccommunicationhandbook.util.Comon.MY_PREFS_FILE;

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

    private AppCompatButton btn_logout;

    private AppCompatButton btn_point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvName = findViewById(R.id.txvName);

        btn_request = findViewById(R.id.btn_request);
        pointService = PointRepository.getInstance(getApplication());

        initView();
    }


    private void initView() {
        btn_profile = findViewById(R.id.btn_profile);
        btn_student_card = findViewById(R.id.btn_student_card);
        btn_rate = findViewById(R.id.btn_rate);
        btn_schedule = findViewById(R.id.btn_schedule);
        btn_point = findViewById(R.id.btn_points);
        btnMessage = findViewById(R.id.btn_message);
        btn_request= findViewById(R.id.btn_request);
        btn_logout= findViewById(R.id.btn_student_logout);
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

                // setContentView(R.layout.activity_schedule);
                FragmentManager fm = getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                startActivity(intent);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserLocalStore userLocalStore= new UserLocalStore(getApplicationContext());
                userLocalStore.resetUserLocal();
                Intent intent= new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

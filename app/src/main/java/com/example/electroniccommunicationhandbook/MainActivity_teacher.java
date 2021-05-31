package com.example.electroniccommunicationhandbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.electroniccommunicationhandbook.ui.authentication.login.Login;
import com.example.electroniccommunicationhandbook.ui.teacher.list_class.ListClass;
import com.example.electroniccommunicationhandbook.ui.teacher.schedule.ScheduleTeacher;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

public class MainActivity_teacher extends AppCompatActivity {
    AppCompatButton btnClass, btnSchedule, btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        btnClass= findViewById(R.id.btn_class);
        btnSchedule= findViewById(R.id.btn_schedule);
        btnLogout= findViewById(R.id.btn_logout_teacher);
        initView();
    }
    public void initView(){
        btnClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), ListClass.class);
                startActivity(intent);
            }
        });
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), ScheduleTeacher.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserLocalStore userLocalStore= new UserLocalStore(getApplicationContext());
                userLocalStore.resetUserLocal();
                Intent intent= new Intent(MainActivity_teacher.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
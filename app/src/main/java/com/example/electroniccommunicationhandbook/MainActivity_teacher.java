package com.example.electroniccommunicationhandbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.ui.authentication.login.Login;
import com.example.electroniccommunicationhandbook.ui.teacher.list_class.ListClass;
import com.example.electroniccommunicationhandbook.ui.teacher.schedule.ScheduleTeacher;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import com.example.electroniccommunicationhandbook.entity.OffRequest;
import com.example.electroniccommunicationhandbook.ui.teacher.list_class.ListClass;
import com.example.electroniccommunicationhandbook.ui.teacher.notification.NotificationTeacherViewActivity;
import com.example.electroniccommunicationhandbook.ui.teacher.notification.TeacherNotificationActivity;
import com.example.electroniccommunicationhandbook.ui.teacher.off_request.OffRequestActivity;


public class MainActivity_teacher extends AppCompatActivity {
    AppCompatButton btnClass, btnSchedule, btnLogout;

    AppCompatButton btn_createNotification;
    AppCompatButton btn_offRequest;
    TextView tv_username;
    AppCompatButton  btn_viewNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        btnClass = findViewById(R.id.btn_class);
        btnSchedule = findViewById(R.id.btn_schedule);
        btnLogout = findViewById(R.id.btn_logout_teacher);
        btn_createNotification = findViewById(R.id.btn_notification_new);
        btn_offRequest = findViewById(R.id.btn_send_off_request);
        tv_username= findViewById(R.id.tv_username_teacher);
        btn_viewNotification= findViewById(R.id.btn_notification);
        initView();
        getInfo();
    }

    public void initView() {
        btnClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListClass.class);
                startActivity(intent);
            }
        });

        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScheduleTeacher.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserLocalStore userLocalStore = new UserLocalStore(getApplicationContext());
                userLocalStore.resetUserLocal();
                Intent intent = new Intent(MainActivity_teacher.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();


            }
        });

        btn_viewNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), NotificationTeacherViewActivity.class);
                startActivity(intent);
            }
        });

        btn_createNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeacherNotificationActivity.class);
                startActivity(intent);
            }
        });

        btn_offRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OffRequestActivity.class);
                startActivity(intent);
            }
        });

    }
    public void getInfo(){
        UserLocalStore userLocalStore;
        userLocalStore = new UserLocalStore(getApplicationContext());
        Student student = new Student();
        try{ student = userLocalStore.getStudentLocal();
            tv_username.setText(student.getName());}
        catch (Exception ex){}
    }

}
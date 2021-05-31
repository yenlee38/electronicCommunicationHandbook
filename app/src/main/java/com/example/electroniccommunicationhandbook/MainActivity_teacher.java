package com.example.electroniccommunicationhandbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.electroniccommunicationhandbook.entity.OffRequest;
import com.example.electroniccommunicationhandbook.ui.teacher.list_class.ListClass;
import com.example.electroniccommunicationhandbook.ui.teacher.notification.TeacherNotificationActivity;
import com.example.electroniccommunicationhandbook.ui.teacher.off_request.OffRequestActivity;

public class MainActivity_teacher extends AppCompatActivity {
    AppCompatButton btnClass;
    AppCompatButton btn_createNotification;
    AppCompatButton btn_offRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        btnClass= findViewById(R.id.btn_class);
        btn_createNotification= findViewById(R.id.btn_notification_new);
        btn_offRequest = findViewById(R.id.btn_send_off_request);
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

        btn_createNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), TeacherNotificationActivity.class);
                startActivity(intent);
            }
        });

        btn_offRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), OffRequestActivity.class);
                startActivity(intent);
            }
        });
    }


}
package com.example.electroniccommunicationhandbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.electroniccommunicationhandbook.ui.teacher.list_class.ListClass;

public class MainActivity_teacher extends AppCompatActivity {
    AppCompatButton btnClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        btnClass= findViewById(R.id.btn_class);
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
    }
}
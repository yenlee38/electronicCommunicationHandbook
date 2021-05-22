package com.example.electroniccommunicationhandbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.electroniccommunicationhandbook.ui.student.card.CardActivity;
import com.example.electroniccommunicationhandbook.ui.student.rate.RateTeacherActivity;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton btn_student_card;
    private AppCompatButton btn_rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        btn_student_card = findViewById(R.id.btn_student_card);
        btn_rate = findViewById(R.id.btn_rate);

        btn_student_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CardActivity.class);
                startActivity(intent);
                setContentView(R.layout.activity_card_student);

            }
        });

        btn_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RateTeacherActivity.class);
                startActivity(intent);
                setContentView(R.layout.activity_rate_teacher);
            }
        });
    }
}
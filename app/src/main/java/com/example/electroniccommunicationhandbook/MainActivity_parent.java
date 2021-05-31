package com.example.electroniccommunicationhandbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.ui.schedule.ScheduleActivity;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import java.time.Year;
import java.util.Calendar;

public class MainActivity_parent extends AppCompatActivity {

    private AppCompatButton btn_schedule;
    private TextView tv_username;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parent);
        initView();
    }

    private void initView(){
        userLocalStore = new UserLocalStore(getApplication());
        btn_schedule = findViewById(R.id.btn_schedule);
        tv_username = findViewById(R.id.tv_username);
       try{ tv_username.setText(userLocalStore.getParentLocal().getName());}catch (Exception e){Log.e("null name: ", e.getMessage());}

        btn_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScheduleActivity scheduleActivity = new ScheduleActivity();
                Calendar calendar = Calendar.getInstance();
                scheduleActivity.YEAR = Year.now().getValue();
                scheduleActivity.DAY = calendar.get(Calendar.DAY_OF_WEEK);
                if(calendar.get(Calendar.MONTH) < 8) // < 8 is semster 2
                    scheduleActivity.SEMESTER = 2;
                else scheduleActivity.SEMESTER = 1;
                Intent intent = new Intent(getApplicationContext(), scheduleActivity.getClass());
                startActivity(intent);
                // setContentView(R.layout.activity_schedule);

            }
        });

    }
}
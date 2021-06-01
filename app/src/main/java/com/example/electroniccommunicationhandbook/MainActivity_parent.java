package com.example.electroniccommunicationhandbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.entity.Parent;
import com.example.electroniccommunicationhandbook.ui.profile.ProfileActivity;
import com.example.electroniccommunicationhandbook.ui.schedule.ScheduleActivity;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import java.time.Year;
import java.util.Calendar;

import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.ui.authentication.login.Login;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

public class MainActivity_parent extends AppCompatActivity {
    Button btnLogout;
    TextView tv_username;
    private AppCompatButton btn_schedule,btnProfile;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parent);

        initial();
        initEvent();
        getInfo();
    }
    public void getInfo(){
        UserLocalStore userLocalStore;
        userLocalStore = new UserLocalStore(getApplicationContext());
        Parent parent = new Parent();
        try{ parent = userLocalStore.getParentLocal();
            tv_username.setText(parent.getName());}
        catch (Exception ex){}
    }

    public void initial(){
        btnLogout= findViewById(R.id.btn_logout_parent);
        tv_username= findViewById(R.id.tv_username_parent);
        userLocalStore = new UserLocalStore(getApplication());
        btn_schedule = findViewById(R.id.btn_schedule);
        tv_username = findViewById(R.id.tv_username);
        btnProfile = findViewById(R.id.btn_profile);
    }
    public void initEvent(){
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLocalStore userLocalStore= new UserLocalStore(getApplicationContext());
                userLocalStore.resetUserLocal();
                Intent intent= new Intent(MainActivity_parent.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        initView();
    }

    private void initView(){
 
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
       btnProfile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity_parent.this, ProfileActivity.class);
               startActivity(intent);
           }
       });

    }
}
package com.example.electroniccommunicationhandbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.ui.authentication.login.Login;
import com.example.electroniccommunicationhandbook.ui.message.MainMessage;
import com.example.electroniccommunicationhandbook.ui.profile.ProfileActivity;

import com.example.electroniccommunicationhandbook.ui.schedule.ScheduleActivity;
import com.example.electroniccommunicationhandbook.ui.student.statistic.StatisticActivity;

import com.example.electroniccommunicationhandbook.ui.teacher.list_class.ListClass;
import com.example.electroniccommunicationhandbook.ui.teacher.schedule.ScheduleTeacher;
import com.example.electroniccommunicationhandbook.ui.teacher.statistic.StatisticTeacherActivity;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import com.example.electroniccommunicationhandbook.entity.OffRequest;
import com.example.electroniccommunicationhandbook.ui.teacher.list_class.ListClass;
import com.example.electroniccommunicationhandbook.ui.teacher.notification.NotificationTeacherViewActivity;
import com.example.electroniccommunicationhandbook.ui.teacher.notification.TeacherNotificationActivity;
import com.example.electroniccommunicationhandbook.ui.teacher.off_request.OffRequestActivity;

import java.time.Year;
import java.util.Calendar;


public class MainActivity_teacher extends AppCompatActivity {
    AppCompatButton btnClass, btnSchedule, btnLogout, btnStatistic;
    AppCompatButton btnMessage;
    AppCompatButton btn_createNotification;
    AppCompatButton btn_offRequest;
    TextView tv_username;

    AppCompatButton btn_viewNotification;


    private AppCompatButton btnProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        initView();
        getInfo();
    }

    public void initView() {
        btnProfile = findViewById(R.id.btn_profile);
        btnClass = findViewById(R.id.btn_class);
        btnSchedule = findViewById(R.id.btn_schedule);
        btnLogout = findViewById(R.id.btn_logout_teacher);
        btn_createNotification = findViewById(R.id.btn_notification_new);
        btn_offRequest = findViewById(R.id.btn_send_off_request);
        tv_username = findViewById(R.id.tv_username_teacher);
        btnMessage= findViewById(R.id.btn_message);
        btn_viewNotification = findViewById(R.id.btn_notification);
        btnStatistic = findViewById(R.id.btn_statistic_teacher);

        getInfo();


        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity_teacher.this, ProfileActivity.class);

                startActivity(intent);

            }
        });
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

                ScheduleTeacher scheduleActivity = new ScheduleTeacher();
                Calendar calendar = Calendar.getInstance();
                scheduleActivity.YEAR = Year.now().getValue();
                scheduleActivity.DAY = calendar.get(Calendar.DAY_OF_WEEK)-1;
                if(calendar.get(Calendar.MONTH) < 8) // < 8 is semster 2
                    scheduleActivity.SEMESTER = 2;
                else scheduleActivity.SEMESTER = 1;
                Intent intent = new Intent(getApplicationContext(), scheduleActivity.getClass());
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity_teacher.this, LogoutActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();


            }
        });

        btn_viewNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotificationTeacherViewActivity.class);
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

        btnStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StatisticTeacherActivity.class);
                startActivity(intent);
                //  setContentView(R.layout.activity_card_student);
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
    }

    public void getInfo() {
        UserLocalStore userLocalStore;
        userLocalStore = new UserLocalStore(getApplicationContext());
        Teacher teacher = new Teacher();

        try {
            teacher = userLocalStore.getTeacherLocal();

            try {
                teacher = userLocalStore.getTeacherLocal();
                tv_username.setText(teacher.getName());
            } catch (Exception ex) {
            }
        }
        catch (Exception e){

        }
    }
}
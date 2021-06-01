package com.example.electroniccommunicationhandbook.ui.teacher.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.ui.student.notification.ui.main.PlaceholderFragment;

public class NotificationTeacherViewActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_teacher_view);
        frameLayout= findViewById(R.id.frameLayout);

        PlaceholderFragment firstFragment = PlaceholderFragment.newInstance(3);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, firstFragment);
        fragmentTransaction.commit();

    }
}
package com.example.electroniccommunicationhandbook.ui.teacher.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.electroniccommunicationhandbook.MainActivity_parent;
import com.example.electroniccommunicationhandbook.MainActivity_teacher;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.ui.profile.UpdateSuccessfullyActivity;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

public class TeacherNotificationActivity extends FragmentActivity implements NotificationFragment.FirstFragmentListener {

    private AppCompatButton btn_back;
    private UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_notification);

        btn_back= findViewById(R.id.btn_comeback);
        NotificationFragment firstFragment = new NotificationFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, firstFragment);
        fragmentTransaction.commit();

        userLocalStore= new UserLocalStore(this);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userLocalStore.getRoleLocal()==1)
                {
                    Intent intent= new Intent(getApplicationContext(), MainActivity_teacher.class);
                    startActivity(intent);
                }

            }
        });
    }


    @Override
    public void onButtonClick(String title) {
        Intent intent= new Intent(getApplicationContext(), UpdateSuccessfullyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Title", title);
        bundle.putString("Key","2");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
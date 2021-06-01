package com.example.electroniccommunicationhandbook.ui.teacher.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.MainActivity_parent;
import com.example.electroniccommunicationhandbook.MainActivity_teacher;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Announcement;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.repository.AnnouncementRepository;
import com.example.electroniccommunicationhandbook.ui.student.notification.NotificationAdapter;
import com.example.electroniccommunicationhandbook.ui.student.notification.ui.main.PlaceholderFragment;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import java.util.ArrayList;

public class NotificationTeacherViewActivity extends AppCompatActivity {

    private AnnouncementRepository repository;
    private RecyclerView recyclerView;
    private TextView tv_nodata;
    private AppCompatButton btn_create_notification, btn_back;

    private ArrayList<Announcement> listAnnouncement;
    private UserLocalStore userLocalStore;
    private Context context;
    private Teacher teacher;
    private int role;
    private ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_teacher_view);

        btn_create_notification= findViewById(R.id.btn_create_notification);
        tv_nodata = findViewById(R.id.txv_nodata);
        recyclerView= findViewById(R.id.recyleviewNotification);
        btn_back= findViewById(R.id.btn_comeback);
        /*PlaceholderFragment firstFragment = PlaceholderFragment.newInstance(3);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, firstFragment);
        fragmentTransaction.commit();*/
        tv_nodata.setVisibility(View.INVISIBLE);
        btn_create_notification.setVisibility(View.INVISIBLE);

        context=this;
        userLocalStore =new UserLocalStore(context);
        teacher= new Teacher();
        teacher= userLocalStore.getTeacherLocal();

        progressBar = (ProgressBar) findViewById(R.id.spin_kit1);
        Sprite doubleBounc = new ThreeBounce();
        progressBar.setIndeterminateDrawable(doubleBounc);
        repository= ViewModelProviders.of(this).get(AnnouncementRepository.class);

        repository.findBySenderId( userLocalStore.getTeacherLocal().getTeacherID());
        repository.getAnnouncmentList().observe(this, new Observer<ArrayList<Announcement>>() {
            @Override
            public void onChanged(ArrayList<Announcement> announcements) {
                if(announcements!=null){
                    loadNotification(announcements);
                    btn_create_notification.setVisibility(View.INVISIBLE);
                    tv_nodata.setVisibility(View.INVISIBLE);
                }
                else {
                    tv_nodata.setVisibility(View.VISIBLE);
                    btn_create_notification.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userLocalStore.getRoleLocal()==1)
                {
                    Intent intent= new Intent(getApplicationContext(), MainActivity_teacher.class);
                    startActivity(intent);
                }
                else
                    if(userLocalStore.getRoleLocal()==3){
                        Intent intent= new Intent(getApplicationContext(), MainActivity_parent.class);
                        startActivity(intent);
                    }
            }
        });

    }

    void loadNotification(ArrayList<Announcement> announcements){
        listAnnouncement= new ArrayList<>(announcements);

        NotificationAdapter adapter=new NotificationAdapter(listAnnouncement,context );
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layout= new LinearLayoutManager(context);
        layout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layout);
    }
}
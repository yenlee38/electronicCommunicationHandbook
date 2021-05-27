package com.example.electroniccommunicationhandbook.ui.schedule;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.SchoolTime;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.repository.StudentRepository;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    private RecyclerView rlv_schedule;
    private ScheduleAdapter scheduleAdapter;
    private ImageView img_back;
    private MutableLiveData<List<Class>> classLiveData;
    private int year;
    private static int SEMESTER = 1;
    private static int DAY = 2;
    private AppCompatButton btn_semesterOne;
    private AppCompatButton btn_semesterTwo;
    private StudentRepository studentRepository;
    private UserLocalStore userLocalStore;
    private Student student;
    private AppCompatButton btn_monDay;
    private AppCompatButton btn_tueDay;
    private AppCompatButton btn_wedDay;
    private AppCompatButton btn_thuDay;
    private AppCompatButton btn_friDay;
    private AppCompatButton btn_satDay;
   private List<Class> lSchedule;
    private List<SchoolTime> lSchoolTime;

    public ScheduleActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        userLocalStore = new UserLocalStore(getApplicationContext());
        student = userLocalStore.getStudentLocal();
        studentRepository = new StudentRepository();
        classLiveData = new MutableLiveData<>();
        lSchedule = new ArrayList<Class>();
        lSchoolTime = new ArrayList<SchoolTime>();
        year = 2021;
        initView();

        setClassLiveDataForChangeSemester();
        //

    }


    private void setClassLiveDataForChangeSemester(){
        classLiveData = studentRepository.getSchedule(student.getStudentId(), year, SEMESTER);
        classLiveData.observe(this, new Observer<List<Class>>() {
            @Override
            public void onChanged(List<Class> classes) {
                if(classes != null){
                    lSchedule = classes;
                    scheduleAdapter = new ScheduleAdapter();
                    scheduleAdapter.setmClassOfDay(getListClassOfDay(lSchedule));
                    scheduleAdapter.setlSchoolTime(lSchoolTime);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    rlv_schedule.setAdapter(scheduleAdapter);
                    rlv_schedule.setLayoutManager(linearLayoutManager);
                }
            }
        });
    }

    private void setClassDay(){

        scheduleAdapter.setmClassOfDay(getListClassOfDay(lSchedule));
        scheduleAdapter.setlSchoolTime(lSchoolTime);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rlv_schedule.setAdapter(scheduleAdapter);
        rlv_schedule.setLayoutManager(linearLayoutManager);

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void initView(){
        rlv_schedule = findViewById(R.id.rlv_schedule);
        img_back = findViewById(R.id.img_back);
        btn_semesterOne = findViewById(R.id.btn_semesterOne);
        btn_semesterTwo = findViewById(R.id.btn_semesterTwo);
        btn_monDay = findViewById(R.id.btn_monDay);
        btn_tueDay = findViewById(R.id.btn_tueDay);
        btn_wedDay = findViewById(R.id.btn_wedDay);
        btn_thuDay = findViewById(R.id.btn_thuDay);
        btn_friDay = findViewById(R.id.btn_friDay);
        btn_satDay = findViewById(R.id.btn_satDay);


        btn_monDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_monDay.setBackground(getDrawable(R.drawable.border_btnview));
                btn_monDay.setTextColor(Color.WHITE);
                btn_tueDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_tueDay.setTextColor(Color.BLACK);
                btn_wedDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_wedDay.setTextColor(Color.BLACK);
                btn_thuDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_thuDay.setTextColor(Color.BLACK);
                btn_friDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_friDay.setTextColor(Color.BLACK);
                btn_satDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_satDay.setTextColor(Color.BLACK);
                DAY = 1;
                setClassDay();

            }
        });

        btn_tueDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_tueDay.setBackground(getDrawable(R.drawable.border_btnview));
                btn_tueDay.setTextColor(Color.WHITE);
                btn_monDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_monDay.setTextColor(Color.BLACK);
                btn_wedDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_wedDay.setTextColor(Color.BLACK);
                btn_thuDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_thuDay.setTextColor(Color.BLACK);
                btn_friDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_friDay.setTextColor(Color.BLACK);
                btn_satDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_satDay.setTextColor(Color.BLACK);
                DAY = 2;
                setClassDay();
            }
        });

        btn_wedDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_wedDay.setBackground(getDrawable(R.drawable.border_btnview));
                btn_wedDay.setTextColor(Color.WHITE);
                btn_tueDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_tueDay.setTextColor(Color.BLACK);
                btn_monDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_monDay.setTextColor(Color.BLACK);
                btn_thuDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_thuDay.setTextColor(Color.BLACK);
                btn_friDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_friDay.setTextColor(Color.BLACK);
                btn_satDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_satDay.setTextColor(Color.BLACK);
                DAY = 3;
                setClassDay();
            }
        });

        btn_thuDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_thuDay.setBackground(getDrawable(R.drawable.border_btnview));
                btn_thuDay.setTextColor(Color.WHITE);
                btn_tueDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_tueDay.setTextColor(Color.BLACK);
                btn_wedDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_wedDay.setTextColor(Color.BLACK);
                btn_monDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_monDay.setTextColor(Color.BLACK);
                btn_friDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_friDay.setTextColor(Color.BLACK);
                btn_satDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_satDay.setTextColor(Color.BLACK);
                DAY = 4;
                setClassDay();
            }
        });

        btn_friDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_friDay.setBackground(getDrawable(R.drawable.border_btnview));
                btn_friDay.setTextColor(Color.WHITE);
                btn_tueDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_tueDay.setTextColor(Color.BLACK);
                btn_wedDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_wedDay.setTextColor(Color.BLACK);
                btn_thuDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_thuDay.setTextColor(Color.BLACK);
                btn_monDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_monDay.setTextColor(Color.BLACK);
                btn_satDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_satDay.setTextColor(Color.BLACK);
                DAY = 5;
                setClassDay();
            }
        });

        btn_satDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_satDay.setBackground(getDrawable(R.drawable.border_btnview));
                btn_satDay.setTextColor(Color.WHITE);
                btn_tueDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_tueDay.setTextColor(Color.BLACK);
                btn_wedDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_wedDay.setTextColor(Color.BLACK);
                btn_thuDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_thuDay.setTextColor(Color.BLACK);
                btn_friDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_friDay.setTextColor(Color.BLACK);
                btn_monDay.setBackground(getDrawable(R.drawable.border_btn_white));
                btn_monDay.setTextColor(Color.BLACK);
                DAY = 6;
                setClassDay();
            }
        });



        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_semesterOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SEMESTER = 1;
                btn_semesterOne.setBackground(getDrawable(R.drawable.border_bottom));
                btn_semesterOne.setTextColor(Color.BLACK);
                btn_semesterTwo.setBackground(getDrawable(R.drawable.border_bottom_gray));
                btn_semesterTwo.setTextColor(Color.GRAY);
                setClassLiveDataForChangeSemester();
            }
        });

        btn_semesterTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SEMESTER = 2;
                btn_semesterTwo.setBackground(getDrawable(R.drawable.border_bottom));
                btn_semesterTwo.setTextColor(Color.BLACK);
                btn_semesterOne.setBackground(getDrawable(R.drawable.border_bottom_gray));
                btn_semesterOne.setTextColor(Color.GRAY);
                setClassLiveDataForChangeSemester();
            }
        });
    }


    private void sortClassBySchoolTime(List<Class> lClass){ // Sắp xếp lại thứ tự tiết học
        for(int i = 0; i < lClass.size() - 1; i++)
           for(int j = i + 1; j < lClass.size(); j++)
               if(lClass.get(j).getStartingSchoolTime() < lClass.get(i).getStartingSchoolTime())
                   swapClass(lClass.get(i), lClass.get(j));
    }

    private void swapClass(Class c1, Class c2){
        Class temp = new Class(c1);
        c1 = new Class(c2);
        c2 = new Class(temp);
    }

    private List<Class> getListClassOfDay(List<Class> mClassOfDay){
        List<Class> lClass = new ArrayList<Class>();
        for(int i = 0; i < mClassOfDay.size(); i++) {
            if (isClassOfDay(mClassOfDay.get(i), DAY))
                lClass.add(mClassOfDay.get(i));
        }
        sortClassBySchoolTime(lClass);
        return lClass;
    }

    private boolean isClassOfDay(Class classNow, int dayOfWeek){ // Kiểm tra class đó có phải là thứ trong tuần mà mình cần tìm hay không

        if(classNow.getDayOfWeek() == dayOfWeek)
            return true;
        return false;
    }
}
package com.example.electroniccommunicationhandbook.ui.schedule;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.repository.StudentRepository;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    private List<Class> mClassOfDay = null; // schedule for each day
    private RecyclerView rlv_schedule;
    private ScheduleAdapter scheduleAdapter;
    private ImageView img_back;
    private LiveData<Class> classLiveData;
    private int year;
    private static int SEMESTER = 1;
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


    public ScheduleActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        userLocalStore = new UserLocalStore(getApplicationContext());
        student = userLocalStore.getStudentLocal();
        initView();
        setRecyclerView();

    }

    private void setRecyclerView(){
        scheduleAdapter = new ScheduleAdapter(mClassOfDay);

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
                setClassLiveData();
            }
        });

        btn_semesterTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SEMESTER = 2;
                setClassLiveData();
            }
        });
    }


    private void setClassLiveData(){
        classLiveData = studentRepository.getSchedule(student.getStudentId(), year, SEMESTER);
    }

    private void setListClassForAdapter(){

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

    private int isClassOfDay(Class classNow, int dayOfWeek){ // Kiểm tra class đó có phải là thứ trong tuần mà mình cần tìm hay không

        if(classNow.getDayOfWeek() == dayOfWeek)
            return 1;
        return 0;
    }
}
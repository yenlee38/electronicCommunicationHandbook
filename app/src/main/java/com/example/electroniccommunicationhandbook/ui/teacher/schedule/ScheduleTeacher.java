package com.example.electroniccommunicationhandbook.ui.teacher.schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.MainActivity_teacher;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.ViewModel.TeacherClassViewModel;
import com.example.electroniccommunicationhandbook.common.StudyingYear;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.SchoolTime;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.repository.MainRepository;
import com.example.electroniccommunicationhandbook.repository.StudentRepository;
import com.example.electroniccommunicationhandbook.ui.schedule.ScheduleAdapter;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import java.util.ArrayList;
import java.util.List;

public class ScheduleTeacher extends AppCompatActivity {

    private RecyclerView rlv_schedule;
    private ScheduleTeacherAdapter scheduleTeacherAdapter;
    private ImageView img_back;
    private MutableLiveData<ArrayList<Class>> classLiveData;
    public static int YEAR;
    public static int SEMESTER = 1;
    public static int DAY = 1;
    private AppCompatButton btn_semesterOne;
    private AppCompatButton btn_semesterTwo;
    private StudentRepository studentRepository;
    private UserLocalStore userLocalStore;
    private Teacher teacher;
    private AppCompatButton btn_monDay;
    private AppCompatButton btn_tueDay;
    private AppCompatButton btn_wedDay;
    private AppCompatButton btn_thuDay;
    private AppCompatButton btn_friDay;
    private AppCompatButton btn_satDay;
    private ArrayList<Class> lSchedule;
    private ArrayList<SchoolTime> lSchoolTime;
    private MutableLiveData<ArrayList<SchoolTime>> lSchoolTimeLiveData;
    private Spinner sp_year;
    private TextView tv_nullClass;
    TeacherClassViewModel teacherClassViewModel;
    public ScheduleTeacher() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_teacher);
        initView();
        setlSchoolTime();
        setListStudyingYear();

    }

    private void setListStudyingYear(){
        List<StudyingYear> yearList = new ArrayList<StudyingYear>();
        for(int i = 2018; i < 2025; i++) // create year from 2018 to 2025
            yearList.add(new StudyingYear(i));
        ArrayAdapter<StudyingYear> adapter = new ArrayAdapter<StudyingYear>(getApplicationContext(), android.R.layout.simple_spinner_item, new ArrayList<StudyingYear>(yearList));
        sp_year.setAdapter(adapter);
        sp_year.setSelection(3); // set studying
        sp_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                YEAR = ((StudyingYear)sp_year.getSelectedItem()).getYear();
                setClassLiveDataForChangeSemester(); // change class for year
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setlSchoolTime(){
        lSchoolTimeLiveData = studentRepository.setLSchoolTimeLiveData();
        lSchoolTimeLiveData.observe(this, new Observer<ArrayList<SchoolTime>>() {
            @Override
            public void onChanged(ArrayList<SchoolTime> schoolTimes) {
                if(schoolTimes != null)
                    lSchoolTime = schoolTimes;
            }
        });
    }


    private void setClassLiveDataForChangeSemester(){
        teacherClassViewModel.findClassOfTeacher(teacher.getTeacherID(),SEMESTER,YEAR)
                .observe(this, new Observer<ArrayList<Class>>() {
                    @Override
                    public void onChanged(ArrayList<Class> classes) {
                        if(classes != null ){
                    lSchedule = classes;
                    scheduleTeacherAdapter = new ScheduleTeacherAdapter();
                            scheduleTeacherAdapter.setmClassOfDay(getListClassOfDay(classes));
                            scheduleTeacherAdapter.setlSchoolTime(lSchoolTime);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    rlv_schedule.setAdapter(scheduleTeacherAdapter);
                    rlv_schedule.setLayoutManager(linearLayoutManager);

                    }
                }});

    }

    private void setClassDay(){
        if(lSchedule.size()!= 0)
        {
            tv_nullClass.setText("");
            scheduleTeacherAdapter = new ScheduleTeacherAdapter();
            scheduleTeacherAdapter.setmClassOfDay(getListClassOfDay(lSchedule));
            scheduleTeacherAdapter.setlSchoolTime(lSchoolTime);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            rlv_schedule.setAdapter(scheduleTeacherAdapter);
            rlv_schedule.setLayoutManager(linearLayoutManager);

        }
        else
            tv_nullClass.setText(getResources().getString(R.string.empty_day));
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(ScheduleTeacher.this, MainActivity_teacher.class));
        finish();
    }

    private void initView(){
        userLocalStore = new UserLocalStore(getApplicationContext());
        teacherClassViewModel = ViewModelProviders.of(this).get(TeacherClassViewModel.class);
        teacher = userLocalStore.getTeacherLocal();
        studentRepository = new StudentRepository();
        classLiveData = new MutableLiveData<>();
        lSchedule = new ArrayList<Class>();
        lSchoolTime = new ArrayList<SchoolTime>();
        lSchoolTimeLiveData = new MutableLiveData<>();
        tv_nullClass = findViewById(R.id.tv_nullClass_teacher);
        rlv_schedule = findViewById(R.id.rlv_schedule_teacher);
        sp_year = findViewById(R.id.sp_year_teacher);
        img_back = findViewById(R.id.img_back_teacher);
        btn_semesterOne = findViewById(R.id.btn_semesterOne_teacher);
        btn_semesterTwo = findViewById(R.id.btn_semesterTwo_teacher);
        btn_monDay = findViewById(R.id.btn_monDay_teacher);
        btn_tueDay = findViewById(R.id.btn_tueDay_teacher);
        btn_wedDay = findViewById(R.id.btn_wedDay_teacher);
        btn_thuDay = findViewById(R.id.btn_thuDay_teacher);
        btn_friDay = findViewById(R.id.btn_friDay_teacher);
        btn_satDay = findViewById(R.id.btn_satDay_teacher);


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


    private void sortClassBySchoolTime(List<Class> lClass){ // S???p x???p l???i th??? t??? ti???t h???c
        for(int i = 0; i < lClass.size() - 1; i++)
            for(int j = i + 1; j < lClass.size(); j++)
                if(lClass.get(j).getStartSchoolTime() < lClass.get(i).getStartSchoolTime())
                    swapClass(lClass.get(i), lClass.get(j));
    }

    private void swapClass(Class c1, Class c2){
        Class temp = new Class(c1);
        c1 = new Class(c2);
        c2 = new Class(temp);
    }


    private int isClassOfDay(Class classNow, int dayOfWeek) { // Ki???m tra class ???? c?? ph???i l?? th??? trong tu???n m?? m??nh c???n t??m hay kh??ng

        if (classNow.getClassDayOfWeek() == dayOfWeek)
            return 1;
        return 0;
    }
    private ArrayList<Class> getListClassOfDay(ArrayList<Class> mClassOfDay){
        ArrayList<Class> lClass = new ArrayList<Class>();
        for(int i = 0; i < mClassOfDay.size(); i++) {
            if (mClassOfDay.get(i).getClassDayOfWeek() == DAY)
                lClass.add(mClassOfDay.get(i));
        }
        //  sortClassBySchoolTime(lClass);
        if(lClass.size() != 0)
            tv_nullClass.setText("");
        else tv_nullClass.setText(getResources().getString(R.string.empty_day));
        return lClass;
    }

}
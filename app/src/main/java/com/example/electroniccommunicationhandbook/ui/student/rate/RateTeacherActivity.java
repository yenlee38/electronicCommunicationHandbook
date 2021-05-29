package com.example.electroniccommunicationhandbook.ui.student.rate;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.common.StudyingYear;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.repository.PointRepository;
import com.example.electroniccommunicationhandbook.repository.StudentRepository;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import java.util.ArrayList;
import java.util.List;

public class RateTeacherActivity extends AppCompatActivity {

    private RateTeacherAdapter rateTeacherAdapter;
    private ArrayList<Student_Class> lClass = null;
    private MutableLiveData<ArrayList<Student_Class>> lClassLiveData;
    private RecyclerView rlv_rateTeacher;
    private ImageView img_back;
    UserLocalStore userLocalStore;
    private Student student;
    private StudentRepository studentRepository;
    private static int YEAR = 2021;
    private static int SEMESTER = 1;
    private AppCompatButton btn_semesterOne;
    private AppCompatButton btn_semesterTwo;
    private Spinner sp_year;
    private PointRepository pointRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_teacher);
        initView();
        setRecyclerView();
        setListStudyingYear();

    }

    private void setListRate(){
        lClassLiveData = studentRepository.getRateList(student.getStudentId(), YEAR, SEMESTER);
        Log.e("tess:", "S: " + SEMESTER + " YEAR: " + YEAR + " STUDENT: " + student.getStudentId());
        lClassLiveData.observe(this, new Observer<ArrayList<Student_Class>>() {
            @Override
            public void onChanged(ArrayList<Student_Class> classes) {
                if(classes != null)
                {
                    lClass = classes;
                    setRecyclerView();
                }
            }
        });
    }

    private void setRecyclerView(){ //
        rateTeacherAdapter = new RateTeacherAdapter(lClass);
        rlv_rateTeacher.setAdapter(rateTeacherAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rlv_rateTeacher.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void initView(){
        rlv_rateTeacher = findViewById(R.id.rlv_rateTeacher);
        img_back = findViewById(R.id.img_back);
        btn_semesterOne = findViewById(R.id.btn_semesterOne);
        btn_semesterTwo = findViewById(R.id.btn_semesterTwo);
        sp_year = findViewById(R.id.sp_year);
        rateTeacherAdapter = new RateTeacherAdapter();
        lClass = new ArrayList<Student_Class>();
        lClassLiveData = new MutableLiveData<>();
        studentRepository = new StudentRepository();
        userLocalStore = new UserLocalStore(getApplicationContext()); // get Student
        student = userLocalStore.getStudentLocal();
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
                setListRate();
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
                setListRate();
            }
        });
    }

    private void setListStudyingYear(){
        sp_year = findViewById(R.id.sp_year);
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
                setListRate(); // change class for year
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}

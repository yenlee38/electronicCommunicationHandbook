package com.example.electroniccommunicationhandbook.ui.teacher.list_class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.MainActivity_teacher;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.ViewModel.TeacherClassViewModel;
import com.example.electroniccommunicationhandbook.common.StudyingYear;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import java.util.ArrayList;
import java.util.List;

public class ListClass extends AppCompatActivity {
    ArrayList<Class> listClasses = new ArrayList<>();
    private static final String mSEMESTER = "Semester";
    private static final String mYEAR = "Year";
    AppCompatButton btnSemesterOne, btnSemesterTwo;
    TeacherClassViewModel teacherClassViewModel;
    private SharedPreferences userLocalStore;
    TextView tvTotalClass;
    RecyclerView rcvClasses;
    TextView tvClassEmpty;
    Button btnBack;
    Teacher teacher;
    int semester = 1;
    int studyingYear = 2021;
    Spinner sp_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_classes);
        //---------- Hook-------------
        initial();
        //--------- init-----------
        setListStudyingYear();
        initEvent();

    }
    private void initial(){
        tvTotalClass = findViewById(R.id.tv_class_total_teacher);
        rcvClasses = findViewById(R.id.rcv_Classes_Teacher);
        sp_year = findViewById(R.id.sp_year_teacher);
        btnSemesterOne = findViewById(R.id.btn_semesterOne_Class);
        btnSemesterTwo = findViewById(R.id.btn_semesterTwo_Class);
        teacherClassViewModel = ViewModelProviders.of(this).get(TeacherClassViewModel.class);
        UserLocalStore userLocalStore = new UserLocalStore(getApplicationContext());
        teacher = userLocalStore.getTeacherLocal();
        tvClassEmpty= findViewById(R.id.tv_empty_classes);
        btnBack= findViewById(R.id.btn_classes_main);

    }
    private void setListStudyingYear() {
        //Spinner
        List<StudyingYear> yearList = new ArrayList<StudyingYear>();
        for (int i = 2018; i < 2025; i++) // create year from 2018 to 2025
            yearList.add(new StudyingYear(i));
        ArrayAdapter<StudyingYear> adapter = new ArrayAdapter<StudyingYear>(getApplicationContext(), android.R.layout.simple_spinner_item, new ArrayList<StudyingYear>(yearList));
        sp_year.setAdapter(adapter);
        sp_year.setSelection(3); // set studying
        sp_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studyingYear = ((StudyingYear) sp_year.getSelectedItem()).getYear();
                updateList();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void initEvent() {
        btnSemesterOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                semester = 1;
                btnSemesterOne.setBackground(getDrawable(R.drawable.border_bottom));
                btnSemesterOne.setTextColor(Color.BLACK);
                btnSemesterTwo.setBackground(getDrawable(R.drawable.border_bottom_gray));
                btnSemesterTwo.setTextColor(Color.GRAY);
                updateList();
            }
        });
        btnSemesterTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                semester = 2;
                btnSemesterTwo.setBackground(getDrawable(R.drawable.border_bottom));
                btnSemesterTwo.setTextColor(Color.BLACK);
                btnSemesterOne.setBackground(getDrawable(R.drawable.border_bottom_gray));
                btnSemesterOne.setTextColor(Color.GRAY);
                updateList();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ListClass.this, MainActivity_teacher.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void updateList() {
        teacherClassViewModel.findClassOfTeacher(teacher.getTeacherID(), semester, studyingYear).observe(this,
                new Observer<ArrayList<Class>>() {
                    @Override
                    public void onChanged(ArrayList<Class> classes) {

                        listClasses = new ArrayList<>();
                        listClasses = classes;
                        ListClassAdapter adapter = new ListClassAdapter(listClasses, getApplicationContext());
                        rcvClasses.setLayoutManager(new LinearLayoutManager(getApplication()));

                        rcvClasses.setAdapter(adapter);
                        if (!classes.isEmpty()) {
                            tvTotalClass.setText(String.valueOf(listClasses.size()));
                            tvClassEmpty.setText("");
                        }
                        else
                        {
                            tvTotalClass.setText("0");
                            tvClassEmpty.setText("No teach in this semester");
                        }
                    }
                });
    }

}
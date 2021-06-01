package com.example.electroniccommunicationhandbook.ui.teacher.list_class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.ViewModel.ClassViewModel;
import com.example.electroniccommunicationhandbook.ViewModel.TeacherClassViewModel;
import com.example.electroniccommunicationhandbook.common.StudyingYear;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.ui.authentication.login.Login;

import java.util.ArrayList;
import java.util.List;

public class StudentInClass extends AppCompatActivity {
    AppCompatButton btnBack;
    RecyclerView rcvStudentInClass;
    ArrayList<Student> studentInClass;
    String classId = "0";
    ClassViewModel classViewModel;
    TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_in_class);

        initial();
        initEvent();
        //Get class Id
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            classId = getIntent().getStringExtra("classId");
        }

        updateList(classId);
    }
    public void initial(){
        classViewModel = ViewModelProviders.of(this).get(ClassViewModel.class);
        btnBack = findViewById(R.id.btn_View_Student_Back);
        studentInClass = new ArrayList<>();
        tvEmpty= findViewById(R.id.tv_student_empty);
    }
    public void initEvent(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
    public void updateList(String classId) {
        classViewModel = ViewModelProviders.of(this).get(ClassViewModel.class);

        classViewModel.findStudentInClass(classId).observe(this,
                new Observer<ArrayList<Student>>() {
                    @Override
                    public void onChanged(ArrayList<Student> students) {
                        if (students != null) {
                            studentInClass = new ArrayList<>();
                            studentInClass = students;
                            //New adapter in Recyclerview
                            rcvStudentInClass = findViewById(R.id.rcv_ListStudents);
                            ListStudentsInClassAdapter adapter = new ListStudentsInClassAdapter(students);
                            rcvStudentInClass.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                            rcvStudentInClass.setAdapter(adapter);
                        }
                        else{
                            tvEmpty.setText("Nothing student enroll this class");
                        }
                    }
                });
    }
}


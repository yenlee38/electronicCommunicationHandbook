package com.example.electroniccommunicationhandbook.ui.teacher.list_class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentInClass extends AppCompatActivity {
    Button btnBack;
    RecyclerView rcvStudentInClass;
    List<Student_Class> studentInClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_in_class);
        btnBack = findViewById(R.id.btn_View_Student_Back);
        studentInClass = new ArrayList<>();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Back
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            studentInClass = (List<Student_Class>) getIntent().getSerializableExtra("StudentInClass");
        }
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentInClass.size(); i++) {
            students.add(studentInClass.get(i).getStudent());
        }
        rcvStudentInClass= findViewById(R.id.rcv_ListStudents);
        ListStudentsInClassAdapter adapter = new ListStudentsInClassAdapter(students);
        rcvStudentInClass.setLayoutManager(new LinearLayoutManager( getBaseContext()));
        rcvStudentInClass.setAdapter(adapter);
    }
}


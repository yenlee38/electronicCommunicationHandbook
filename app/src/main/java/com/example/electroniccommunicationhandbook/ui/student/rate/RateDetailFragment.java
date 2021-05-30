package com.example.electroniccommunicationhandbook.ui.student.rate;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.repository.StudentRepository;


public class RateDetailFragment extends Fragment {

    private Student_Class student_class;
    private TextView tv_name;
    private TextView tv_name_subject_rate;
    private TextView edt_comment;
    private AppCompatButton btn_rate_teacher;
    private AppCompatButton btn_rate_1;
    private AppCompatButton btn_rate_2;
    private AppCompatButton btn_rate_3;
    private AppCompatButton btn_rate_4;
    private AppCompatButton btn_rate_5;
    private StudentRepository studentRepository;

    public RateDetailFragment() {
        studentRepository = new StudentRepository();

        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        student_class = new Student_Class();
        student_class = (Student_Class) getArguments().getSerializable("student_class_object");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          View view = inflater.inflate(R.layout.fragment_rate_detail, container, false);
          initView(view);
         return view;
    }

    private void initView(View view){
        tv_name_subject_rate = view.findViewById(R.id.tv_name_subject_rate);
        tv_name = view.findViewById(R.id.tv_name);
        edt_comment = view.findViewById(R.id.edt_comment);
        btn_rate_teacher = view.findViewById(R.id.btn_rate_teacher);
        btn_rate_1 = view.findViewById(R.id.btn_rate_1);
        btn_rate_2 = view.findViewById(R.id.btn_rate_2);
        btn_rate_3 = view.findViewById(R.id.btn_rate_3);
        btn_rate_4 = view.findViewById(R.id.btn_rate_4);
        btn_rate_5 = view.findViewById(R.id.btn_rate_5);

        tv_name_subject_rate.setText(student_class.get_class().getSubject().getName());
        tv_name.setText(student_class.get_class().getTeacher().getName());


        btn_rate_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("btn :" ,"vao");
                student_class.setRating(4);
                Log.e("btn :" ,student_class.getRating() + student_class.getComment());
                Log.e("btn :" ,"vao" + student_class.getStudentClassId().getStudentId() + student_class.getStudentClassId().getClassId() );
                studentRepository.updateStudentClassById(student_class, student_class.getStudentClassId().getStudentId(),  student_class.getStudentClassId().getClassId());
            }
        });
    }
}
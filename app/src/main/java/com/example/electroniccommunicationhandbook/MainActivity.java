package com.example.electroniccommunicationhandbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.repository.MainRepository;
import com.example.electroniccommunicationhandbook.repository.PointRepository;
import com.example.electroniccommunicationhandbook.service.PointService;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;
    //MainRepository mainRepository;
    PointRepository pointService;
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName= findViewById(R.id.txvName);
        btnLogout= findViewById(R.id.hoang);
        pointService=  PointRepository.getInstance();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              //  mainRepository = MainRepository.getInstance();
                Student_Class student_class= new Student_Class();
                Class mclass = new Class();
                Student student = new Student();
                mclass.setClassId("Class1");

                student_class.set_class(mclass);
                student_class.setStudent(student);
                student= pointService.createPointInfo("1");
                if(student!= null)
                tvName.setText(student.getName() +" " + student.getBirthday());
                else
                    tvName.setText("Null roi");
            }
        });
    }


}
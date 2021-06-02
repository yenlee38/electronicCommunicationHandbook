package com.example.electroniccommunicationhandbook.ui.student.rate;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.common.StudyingYear;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.repository.PointRepository;
import com.example.electroniccommunicationhandbook.repository.StudentRepository;
import com.example.electroniccommunicationhandbook.ui.student.point.PointViewActivity;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
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
    public static int YEAR = 2021;
    public static int SEMESTER = 1;
    private AppCompatButton btn_semesterOne;
    private AppCompatButton btn_semesterTwo;
    private Spinner sp_year;
    private TextView tv_nullClass;
    private AppCompatButton btn_viewPoint;
    //private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_teacher);
        initView();
        //SEMESTER = 1;
        //setListRate();
        setListStudyingYear();

    }

    @Override
    public void startActivityFromFragment(@NonNull Fragment fragment, Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityFromFragment(fragment, intent, requestCode, options);
    }

    public void setListRate(){
        lClassLiveData = studentRepository.getRateList(student.getStudentId(), YEAR, SEMESTER);
        Log.e("tess:", "S: " + SEMESTER + " YEAR: " + YEAR + " STUDENT: " + student.getStudentId());
        lClassLiveData.observe(this, new Observer<ArrayList<Student_Class>>() {
            @Override
            public void onChanged(ArrayList<Student_Class> classes) {
                if(classes != null)
                {
                    lClass = classes;
                    setRecyclerView();
                    if(lClass.size()==0)
                        tv_nullClass.setText(getResources().getString(R.string.empty_rate));
                    else tv_nullClass.setText("");
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
        if(lClass.size()==0)
            tv_nullClass.setText(getResources().getString(R.string.empty_rate));
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
        tv_nullClass = findViewById(R.id.tv_nullClass);
        rateTeacherAdapter = new RateTeacherAdapter();
        lClass = new ArrayList<Student_Class>();
        lClassLiveData = new MutableLiveData<>();
        studentRepository = new StudentRepository();
        userLocalStore = new UserLocalStore(getApplicationContext()); // get Student
        student = userLocalStore.getStudentLocal();
        setBtn_semesterChoose(SEMESTER);

        btn_viewPoint = findViewById(R.id.btn_viewPoint);

        btn_viewPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PointViewActivity.class);
                startActivity(intent);
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
        int startYear = 2018; // set
        if(student.getYear() > 0) startYear = student.getYear();
        for(int i = startYear ; i < startYear + 5; i++) // create year from start year to end year
            yearList.add(new StudyingYear(i));
        ArrayAdapter<StudyingYear> adapter = new ArrayAdapter<StudyingYear>(getApplicationContext(), android.R.layout.simple_spinner_item, new ArrayList<StudyingYear>(yearList));
        sp_year.setAdapter(adapter);

//        try {
//            if(Year.now().getValue() < student.getYear() + 8)
//            sp_year.setSelection(Year.now().getValue()); // set studying
//        }catch (Exception e){Log.e("select error", e.getMessage());}

        int index = 0;
        for(int i = 0; i < yearList.size(); i++)
            if(yearList.get(i).getYear() == YEAR){index = i; break;}
        sp_year.setSelection(index);

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

    public AppCompatButton getBtn_semesterOne() {
        return btn_semesterOne;
    }

    public AppCompatButton getBtn_semesterTwo() {
        return btn_semesterTwo;
    }

    public void setBtn_semesterOne(AppCompatButton btn_semesterOne) {
        this.btn_semesterOne = btn_semesterOne;
    }

    public void setBtn_semesterTwo(AppCompatButton btn_semesterTwo) {
        this.btn_semesterTwo = btn_semesterTwo;
    }

    public void setBtn_semesterChoose(int semesterChoose){
        if(semesterChoose == 1){
            getBtn_semesterOne().setBackground(getDrawable (R.drawable.border_bottom));
            getBtn_semesterOne().setTextColor(Color.BLACK);
            getBtn_semesterTwo() .setBackground(getDrawable(R.drawable.border_bottom_gray));
            getBtn_semesterTwo() .setTextColor(Color.GRAY);
        }else {
            getBtn_semesterTwo().setBackground(getDrawable(R.drawable.border_bottom));
            getBtn_semesterTwo().setTextColor(Color.BLACK);
            getBtn_semesterOne().setBackground(getDrawable(R.drawable.border_bottom_gray));
            getBtn_semesterOne().setTextColor(Color.GRAY);
        }
    }

}

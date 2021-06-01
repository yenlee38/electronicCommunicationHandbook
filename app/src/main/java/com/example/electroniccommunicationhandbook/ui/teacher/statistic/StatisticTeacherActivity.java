package com.example.electroniccommunicationhandbook.ui.teacher.statistic;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.common.StudyingYear;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.repository.PointRepository;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StatisticTeacherActivity extends AppCompatActivity {

    private UserLocalStore userLocalStore;
    private PointRepository repository;

    private String teacherId;

    private MutableLiveData<ArrayList<Class>> listAllClass = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Student_Class>> listStudent = new MutableLiveData<>();
    private ArrayList<Student_Class> allStudentInSemester = new ArrayList<>();
    private List<StudyingYear> yearList;

    MaterialToolbar toolbar;
    TabLayout topTab;

    TextView tvStudentPass;
    Button btnViewStatistic;
    Spinner spYear;

    private int year;
    private int semester = 0;
    int selectPosition = 0;

    int[] result = {0, 0};
    String[] label = {"Pass", "Fail"};
    String studentPass = "0/0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_teacher);

        yearList = new ArrayList<StudyingYear>();

        userLocalStore = new UserLocalStore(this);
        teacherId = userLocalStore.getTeacherLocal().getTeacherID();
        repository = new PointRepository(getApplication());
        repository = ViewModelProviders.of(this).get(PointRepository.class);


        // Get text view: GPA, Grade, Pass
        tvStudentPass = (TextView)findViewById(R.id.tv_no_student_pass);

        // Toolbar on Top
        toolbar = (MaterialToolbar) findViewById(R.id.toolbar_teacher);
        // Set Event for Toolbar: Back to Home
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        // Get spinner Semester
        spYear = (Spinner)findViewById(R.id.sp_year_teacher);

        // Get Tab
        topTab = (TabLayout)findViewById(R.id.tab_semester_teacher);

        topTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                semester = topTab.getSelectedTabPosition() + 1;
                repository.findAllStudentOfTeacherInASemester(teacherId, year, semester);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                semester = topTab.getSelectedTabPosition() + 1;
                repository.findAllStudentOfTeacherInASemester(teacherId, year, semester);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                semester = topTab.getSelectedTabPosition() + 1;
                repository.findAllStudentOfTeacherInASemester(teacherId, year, semester);
            }
        });

        TabLayout.Tab tab = topTab.getTabAt(0);
        tab.select();

        btnViewStatistic = (Button)findViewById(R.id.btn_view_statistic_teacher);
        btnViewStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudyingYear select = (StudyingYear)spYear.getSelectedItem();
                year = select.getYear();
                selectPosition = spYear.getSelectedItemPosition();
                repository.findAllStudentOfTeacherInASemester(teacherId, year, semester);
            }
        });

        // Get list all student_class
        listAllClass = repository.findAllClassOfTeacher(teacherId);
        listAllClass.observe(this, new Observer<ArrayList<Class>>() {
            @Override
            public void onChanged(ArrayList<Class> all_classes) {
                // If list student_class not empty
                if (all_classes.size() > 0) {
                    // Filter student_class by student
                    // Then add year of student_class to list year
                    ArrayList<String> temp = new ArrayList<>();
                    for (Class i : all_classes) {
                        if (i.getTeacher().getTeacherID().equals(teacherId)) {
                            if (!temp.contains(String.valueOf(i.getStudyingYear() ))) {

                                temp.add(String.valueOf(i.getStudyingYear()));
                                yearList.add(new StudyingYear(i.getStudyingYear()));
                            }
                        }
                    }

                    // Create adapter and set it for spinner
                    ArrayAdapter<StudyingYear> adapter = new ArrayAdapter<StudyingYear>(StatisticTeacherActivity.this, android.R.layout.simple_spinner_item, yearList);
                    spYear.setAdapter(adapter);

                    // Select first year
                    year = adapter.getItem(0).getYear();

                    Log.e("Test", "----------- List Student - before -----------");
                    Log.e("Test Semester", String.valueOf(teacherId));
                    Log.e("Test year", String.valueOf(year));
                    Log.e("Test Semester", String.valueOf(semester));
                    Log.e("Test All Student", String.valueOf(allStudentInSemester.size()));

                    // Get list student_class of student in a semester
                    repository.findAllStudentOfTeacherInASemester(teacherId, year, semester);
                    calculateResult(allStudentInSemester);
                }
            }
        });

        // Get list student_class of student in a semester
        listStudent = repository.findAllStudentOfTeacherInASemester(teacherId, year, semester);
        listStudent.observe(this, new Observer<ArrayList<Student_Class>>() {
            @Override
            public void onChanged(ArrayList<Student_Class> student_classes) {

                allStudentInSemester = student_classes;
                Log.e("Test", "----------- List Student - after -----------");
                Log.e("Test Semester", String.valueOf(teacherId));
                Log.e("Test year", String.valueOf(year));
                Log.e("Test Semester", String.valueOf(semester));
                Log.e("Test All Student", String.valueOf(allStudentInSemester.size()));

                // If list student_class not empty
                if (student_classes.size() > 0) {
                    findViewById(R.id.pie_chart_teacher).setVisibility(View.VISIBLE);
                    findViewById(R.id.tv_no_data_teacher).setVisibility(View.GONE);
                    // Calculate GPA, Grade, Pass and Number of Pass/Fail Credit
                    // Then show it in view
                    calculateResult(student_classes);
                } else {
                    studentPass = "0/0";
                    tvStudentPass.setText(studentPass);
                    findViewById(R.id.pie_chart_teacher).setVisibility(View.GONE);
                    findViewById(R.id.tv_no_data_teacher).setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void calculateResult(@NotNull ArrayList<Student_Class> student_classes) {
        float totalStudent = 0;
        int passStudent = 0;

        for (Student_Class i:student_classes) {
            totalStudent ++;
            if((i.getFinalMark()+i.getMiddleMark())/2>5) {
                passStudent ++;
            }
        }
        Log.e("Test", "----------- calculate -----------");
        Log.e("Test total Student", String.valueOf(totalStudent));
        Log.e("Test pass Student", String.valueOf(passStudent));

        studentPass = (int)passStudent+"/"+(int)totalStudent;

        int[] newResult = {(int)passStudent, (int)(totalStudent - passStudent)};
        result = newResult;

        // Show data in view
        populateData();
    }

    private void populateData() {
        tvStudentPass.setText(studentPass);
        drawPieChart();
        spYear.setSelection(selectPosition);
    }

    private void drawPieChart() {
        // Populating a list of PieEntries:
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < result.length; i++) {
            pieEntries.add(new PieEntry(result[i], label[i]));
        }
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        int[] arrayColors= {Color.rgb(114, 0, 202),
                            Color.rgb(226, 84, 255)};

        pieDataSet.setColors(arrayColors);

        //pieDataSet.setValueTextSize(16f);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(16f);
        //pieData.setValueTypeface(Typeface.MONOSPACE);
        pieData.setValueTextColor(Color.WHITE);

        // Get the chart
        PieChart pieChart = (PieChart)findViewById(R.id.pie_chart_teacher);
        pieChart.setData(pieData);
        pieChart.setEntryLabelTextSize(16f);
        //pieChart.setEntryLabelTypeface(Typeface.MONOSPACE);
        pieChart.animateY(1000);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);

        // Show the chart
        pieChart.invalidate();
    }
}
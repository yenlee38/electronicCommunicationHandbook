package com.example.electroniccommunicationhandbook.ui.student.statistic;

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

public class StatisticActivity extends AppCompatActivity {

    private UserLocalStore userLocalStore;
    private PointRepository repository;
    private String studentId;

    private MutableLiveData<ArrayList<Student_Class>> listAllClass = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Student_Class>> listPoint = new MutableLiveData<>();
    private List<StudyingYear> yearList;

    MaterialToolbar toolbar;
    TabLayout topTab;
    TextView tvGPA;
    TextView tvGrade;
    TextView tvPass;
    Button btnViewStatistic;
    Spinner spYear;

    private int year;
    private int semester = 0;
    int selectPosition = 0;

    int[] result = {0, 0};
    String label[] = {"Pass", "Fail"};
    float gpa = 0.0f;
    char grade = 'F';
    String pass = "0/0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        yearList = new ArrayList<StudyingYear>();

        userLocalStore = new UserLocalStore(this);
        studentId = userLocalStore.getStudentLocal().getStudentId();
        repository = new PointRepository(getApplication());
        repository = ViewModelProviders.of(this).get(PointRepository.class);

        // Get text view: GPA, Grade, Pass
        tvGPA = (TextView)findViewById(R.id.tv_gpa);
        tvGrade = (TextView)findViewById(R.id.tv_grade);
        tvPass = (TextView)findViewById(R.id.tv_pass);

        // Toolbar on Top
        toolbar = (MaterialToolbar) findViewById(R.id.toolbar);
        // Set Event for Toolbar: Back to Home
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        // Get spinner Semester
        spYear = (Spinner)findViewById(R.id.sp_year);

        // Get Tab
        topTab = (TabLayout)findViewById(R.id.tab_semester);

        topTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                semester = topTab.getSelectedTabPosition() + 1;
                repository.findPointEverySemester(studentId, year, semester);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                semester = topTab.getSelectedTabPosition() + 1;
                repository.findPointEverySemester(studentId, year, semester);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                semester = topTab.getSelectedTabPosition() + 1;
                repository.findPointEverySemester(studentId, year, semester);
            }
        });

        TabLayout.Tab tab = topTab.getTabAt(0);
        tab.select();

        btnViewStatistic = (Button)findViewById(R.id.btn_view_statistic);
        btnViewStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudyingYear select = (StudyingYear)spYear.getSelectedItem();
                year = select.getYear();
                selectPosition = spYear.getSelectedItemPosition();
                repository.findPointEverySemester(studentId, year, semester);
            }
        });

        // Get list all student_class
        listAllClass = repository.findAllLiveData();
        listAllClass.observe(this, new Observer<ArrayList<Student_Class>>() {
            @Override
            public void onChanged(ArrayList<Student_Class> all_classes) {
                // If list student_class not empty
                if (all_classes.size() > 0) {
                    // Filter student_class by student
                    // Then add year of student_class to list year
                    ArrayList<String> temp = new ArrayList<>();
                    for (Student_Class i : all_classes) {
                        if (i.getStudentClassId().getStudentId().equals(studentId)) {
                            if (!temp.contains(String.valueOf(i.get_class().getStudyingYear() ))) {
                                temp.add(String.valueOf(i.get_class().getStudyingYear()));
                                yearList.add(new StudyingYear(i.get_class().getStudyingYear()));
                            }
                        }
                    }

                    // Create adapter and set it for spinner
                    ArrayAdapter<StudyingYear> adapter = new ArrayAdapter<StudyingYear>(StatisticActivity.this, android.R.layout.simple_spinner_item, yearList);
                    spYear.setAdapter(adapter);

                    // Select first year
                    year = adapter.getItem(0).getYear();

                    // Get list student_class of student in a semester
                    repository.findPointEverySemester(studentId, year, semester);
                }
            }
        });

        // Get list student_class of student in a semester
        listPoint = repository.findPointEverySemester(studentId, year, semester);
        listPoint.observe(this, new Observer<ArrayList<Student_Class>>() {
            @Override
            public void onChanged(ArrayList<Student_Class> student_classes) {
                // If list student_class not empty
                if (student_classes.size() > 0) {
                    findViewById(R.id.pie_chart).setVisibility(View.VISIBLE);
                    findViewById(R.id.tv_no_data).setVisibility(View.GONE);
                    // Calculate GPA, Grade, Pass and Number of Pass/Fail Credit
                    // Then show it in view
                    calculateResult(student_classes);
                } else {
                    gpa = 0.0f;
                    grade = 'F';
                    pass = "0/0";
                    tvGPA.setText(String.valueOf(gpa));
                    tvGrade.setText(String.valueOf(grade));
                    tvPass.setText(pass);
                    findViewById(R.id.pie_chart).setVisibility(View.GONE);
                    findViewById(R.id.tv_no_data).setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public char Grade(float result){
        if(result>9)
            return 'A';
        else if(result>8)
            return 'B';
        else if(result>6)
            return 'C' ;
        else if(result>4)
            return 'D';
        return 'F';
    }

    private void calculateResult(@NotNull ArrayList<Student_Class> student_classes) {
        float sumPoint = 0;
        int totalCredit = 0;
        int passSubject = 0;
        int totalSubject= 0;
        int passCredit = 0;

        for (Student_Class i:student_classes) {
            totalCredit+=i.get_class().getSubject().getNumberOfCredit();
            sumPoint += (i.getFinalMark()+i.getMiddleMark())/2*i.get_class().getSubject().getNumberOfCredit();
            if((i.getFinalMark()+i.getMiddleMark())/2>5) {
                passSubject ++;
                passCredit += i.get_class().getSubject().getNumberOfCredit();
            }
            totalSubject ++;
        }

        sumPoint = sumPoint/totalCredit;
        sumPoint = (float)Math.round(sumPoint*100)/100;
        gpa = sumPoint;
        grade = Grade(gpa);
        pass = passSubject+"/"+totalSubject;

        int[] newResult = {(int)passCredit, (int)(totalCredit - passCredit)};
        result = newResult;

        // Show data in view
        populateData();
    }

    private void populateData() {
        tvGPA.setText(String.valueOf(gpa));
        tvGrade.setText(String.valueOf(grade));
        tvPass.setText(pass);
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
        int[] arrayColors= {Color.rgb(186, 0, 13),
                Color.rgb(255, 121, 97)};

        pieDataSet.setColors(arrayColors);

        //pieDataSet.setValueTextSize(16f);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(16f);
        //pieData.setValueTypeface(Typeface.MONOSPACE);
        pieData.setValueTextColor(Color.WHITE);

        // Get the chart
        PieChart pieChart = (PieChart)findViewById(R.id.pie_chart);
        pieChart.setData(pieData);
        pieChart.setEntryLabelTextSize(16f);
        pieChart.setEntryLabelColor(Color.WHITE);
        //pieChart.setEntryLabelTypeface(Typeface.MONOSPACE);
        pieChart.animateY(1000);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);

        // Show the chart
        pieChart.invalidate();
    }
}
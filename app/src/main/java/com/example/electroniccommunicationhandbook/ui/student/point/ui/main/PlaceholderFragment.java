package com.example.electroniccommunicationhandbook.ui.student.point.ui.main;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.common.StudyingYear;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Point;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.repository.PointRepository;
import com.example.electroniccommunicationhandbook.ui.schedule.ScheduleAdapter;
import com.example.electroniccommunicationhandbook.ui.student.point.PointAdapter;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import java.security.acl.Owner;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView recyclerView;
    private TextView txvErrorload;
    private Spinner spnStudyingYear;
    private PointAdapter pointAdapter;


    private ArrayList<Student_Class> listpoint;
    private UserLocalStore userLocalStore;
    private Student studentLocal ;
    private Context context;
    private PointRepository repository;
    private int role;
    private int semester;
    private ProgressBar progressBar ;

    private TextView tv_gpa, tv_numpass;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        semester= index;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_point_view, container, false);
        recyclerView =root.findViewById(R.id.recycelviewPoint);
        context= this.getContext();

        tv_gpa= root.findViewById(R.id.tv_gpa);
        tv_numpass= root.findViewById(R.id.tv_numPass);
        txvErrorload= root.findViewById(R.id.txv_errorload);
        spnStudyingYear= root.findViewById(R.id.spinnerYear);

         progressBar = (ProgressBar) root.findViewById(R.id.spin_kit);
        Sprite doubleBounce = new ThreeBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

        userLocalStore = new UserLocalStore(context);
        role = userLocalStore.getRoleLocal();

        repository= ViewModelProviders.of(this).get(PointRepository.class);
        repository.getStudentClassList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Student_Class>>() {
            @Override
            public void onChanged(ArrayList<Student_Class> student_classes) {
                if(student_classes!=null)
                {
                    loadPoint(student_classes, root);
                }
                else
                {
                    txvErrorload.setVisibility(View.VISIBLE);
                }

                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        if(role==2){
            studentLocal= userLocalStore.getStudentLocal();
        }

        setListStudyingYear(root);
        return root;
    }

    private void setListStudyingYear(View root){
        List<StudyingYear> yearList = new ArrayList<StudyingYear>();
        for(int i = 2018; i < 2022; i++) // create year from 2018 to 2025
            yearList.add(new StudyingYear(i));
        ArrayAdapter<StudyingYear> adapter = new ArrayAdapter<StudyingYear>(context, android.R.layout.simple_spinner_item, new ArrayList<StudyingYear>(yearList));
        spnStudyingYear.setAdapter(adapter);

        spnStudyingYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spnStudyingYear.setVisibility(View.VISIBLE);
                int YEAR = ((StudyingYear)spnStudyingYear.getSelectedItem()).getYear();
                Log.e("Selected ", "onItemSelected: "+ YEAR);
                setClassLiveDataForChangeSemester(YEAR); // change class for year
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnStudyingYear.setSelection(3);
    }

    private void setClassLiveDataForChangeSemester(int year){
        repository.findPointEverySemester(studentLocal.getStudentId(), year,semester);
    }

    void loadPoint(ArrayList<Student_Class> student_classes, View root){

        listpoint =new ArrayList<Student_Class>(student_classes);

        txvErrorload.setVisibility(View.INVISIBLE);

        pointAdapter =new PointAdapter(listpoint,root.getContext() );
        recyclerView.setAdapter(pointAdapter);
        LinearLayoutManager layout= new LinearLayoutManager(root.getContext());
        layout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layout);

        float temp = 0;
        int creditTotal=0;
        int numpass=0;
        int totalSubject=0;
        for (Student_Class i:listpoint) {
            creditTotal+=i.get_class().getSubject().getNumberOfCredit();
            temp+= (i.getFinalMark()+i.getMiddleMark())/2*i.get_class().getSubject().getNumberOfCredit();
            if((i.getFinalMark()+i.getMiddleMark())/2>5)
                numpass++;
            totalSubject++;
        }

        temp= temp/creditTotal;
        temp= (float) Math.round(temp*100)/100;
        tv_gpa.setText(String.valueOf(temp));
        tv_numpass.setText(""+numpass+"/"+totalSubject);
    }


}
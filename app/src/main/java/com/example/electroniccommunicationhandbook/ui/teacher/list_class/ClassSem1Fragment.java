package com.example.electroniccommunicationhandbook.ui.teacher.list_class;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.ViewModel.TeacherClassViewModel;
import com.example.electroniccommunicationhandbook.entity.Teacher;

import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.example.electroniccommunicationhandbook.entity.Class;

import java.util.ArrayList;

public class ClassSem1Fragment extends Fragment {
    private static final String SEMESTER = "Semester";
    private static final String YEAR = "Year";


    TextView tvTotalClass, tvTotalStudent;
    RecyclerView rcvClasses;
    ArrayList<Class> listClass;
    TeacherClassViewModel teacherClassViewModel;
    Teacher teacher;
    static int semester = 1;
    static int studyingYear = 2021;
    public static void setSemester(int semester) {
        ClassSem1Fragment.semester = semester;
    }
    public void setStudyingYear(int studyingYear) {
        ClassSem1Fragment.studyingYear = studyingYear;
        this.updateList();
    }
    public ClassSem1Fragment() {
    }


    public static ClassSem1Fragment newInstance(int semester) {
        setSemester(semester);
        ClassSem1Fragment fragment = new ClassSem1Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt(SEMESTER, semester);
        bundle.putInt(YEAR, studyingYear);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int mSemester = 1;
        int mYear = 2021;
        if (getArguments() != null) {
            mSemester = getArguments().getInt(SEMESTER);
            mYear = getArguments().getInt(YEAR);
        }

        semester = mSemester;
        studyingYear = mYear;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_class_semp1, container, false);
        tvTotalClass = view.findViewById(R.id.tv_class_total_sem1);
        tvTotalStudent = view.findViewById(R.id.tv_class_total_sem1);

        teacherClassViewModel = ViewModelProviders.of(this).get(TeacherClassViewModel.class);
        rcvClasses = view.findViewById(R.id.rcv_Classes);
        UserLocalStore userLocalStore = new UserLocalStore(getContext());
        teacher = userLocalStore.getTeacherLocal();

        updateList();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        updateList();
    }
    public void updateList() {

        teacherClassViewModel.findClassOfTeacher(teacher.getTeacherID(), semester, studyingYear).observe(getViewLifecycleOwner(),
                new Observer<ArrayList<Class>>() {
                    @Override
                    public void onChanged(ArrayList<Class> classes) {
                        //Log.e(String.valueOf(semester), String.valueOf(studyingYear));
                        listClass = new ArrayList<>();
                        listClass = classes;
                        ListClassAdapter adapter = new ListClassAdapter(listClass, getContext());
                        rcvClasses.setLayoutManager(new LinearLayoutManager(getContext()));
                        tvTotalClass.setText(String.valueOf(listClass.size()));

                        rcvClasses.setAdapter(adapter);
                    }
                });
    }
}

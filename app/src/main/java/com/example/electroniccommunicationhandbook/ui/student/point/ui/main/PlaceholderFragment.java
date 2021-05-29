package com.example.electroniccommunicationhandbook.ui.student.point.ui.main;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Point;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.repository.PointRepository;
import com.example.electroniccommunicationhandbook.ui.student.point.PointAdapter;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

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
    private PointAdapter pointAdapter;
    private ArrayList<Student_Class> listpoint;
    private UserLocalStore userLocalStore;
    private Context context;
    private PointRepository repository;
    private int role;
    private int semester;

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

        userLocalStore = new UserLocalStore(context);
        role = userLocalStore.getRoleLocal();

        txvErrorload= root.findViewById(R.id.txv_errorload);

        Student student = new Student();
        if(role==2){

            student= userLocalStore.getStudentLocal();
        }


        repository= ViewModelProviders.of(this).get(PointRepository.class);
        repository.findPointEverySemester(student.getStudentId(), 2021,semester);
        repository.getStudentClassList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Student_Class>>() {
            @Override
            public void onChanged(ArrayList<Student_Class> student_classes) {
                if(student_classes!=null)
                {
                    txvErrorload.setVisibility(View.INVISIBLE);
                    listpoint =new ArrayList<Student_Class>(student_classes);
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

                    tv_gpa.setText(String.valueOf(temp));
                    tv_numpass.setText(""+numpass+"/"+totalSubject);

                }
                else
                {
                    txvErrorload.setVisibility(View.VISIBLE);
                }

            }
        });


        return root;
    }
}
package com.example.electroniccommunicationhandbook.ui.student.fee.ui.main;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.common.StudyingYear;
import com.example.electroniccommunicationhandbook.entity.FeeInfor;
import com.example.electroniccommunicationhandbook.entity.Parent;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.repository.FeeRepository;
import com.example.electroniccommunicationhandbook.repository.PointRepository;
import com.example.electroniccommunicationhandbook.ui.student.fee.FeeAdapter;
import com.example.electroniccommunicationhandbook.ui.student.point.PointAdapter;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView recyclerView;
    private FeeAdapter feeAdapter;
    private FeeRepository repository;
    private Context context;
    private int semester;
    private int year;
    private int role;
    private Student studentLocal;
    private UserLocalStore userLocalStore;
    private Parent parentLocal;
    private Spinner spinnerYear;
    private ProgressBar progressBar;
    private TextView tv_totalfee,tv_numSubject;
   // private ArrayList<Fee> listFee;

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
        View root = inflater.inflate(R.layout.fragment_fee_view, container, false);
        recyclerView  = root.findViewById(R.id.recycelviewFee);
        spinnerYear= root.findViewById(R.id.spinnerYear);
        tv_numSubject= root.findViewById(R.id.tv_noSubject);
        tv_totalfee= root.findViewById(R.id.tv_totalfee);
        context= this.getContext();
        setSpinnerYearValue();

        progressBar = (ProgressBar) root.findViewById(R.id.spin_kit);
        Sprite doubleBounce = new ThreeBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

        userLocalStore= new UserLocalStore(context);
        role = userLocalStore.getRoleLocal();
        if(role==2){
            studentLocal= userLocalStore.getStudentLocal();
        }
        if(role==3)
        {
            parentLocal= userLocalStore.getParentLocal();
            studentLocal= parentLocal.getStudent();
        }
        repository= ViewModelProviders.of(this).get(FeeRepository.class);

        repository.getFeeInforMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<FeeInfor>>() {
            @Override
            public void onChanged(ArrayList<FeeInfor> feeInfors) {
                if(feeInfors!=null) {
                    loadFee(feeInfors,root);
                }
                else {

                    loadFee(new ArrayList<FeeInfor>(),root);
                    Toast.makeText(context,"No data to load !!",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        spinnerYearSelectionChange();

        //repository.getFeeInfor(studentLocal.getStudentId(),year, semester);


        return root;
    }

    public void spinnerYearSelectionChange(){

        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                progressBar.setVisibility(View.VISIBLE);
                spinnerYear.setVisibility(View.VISIBLE);
                year= ((StudyingYear)spinnerYear.getSelectedItem()).getYear();
                Log.e("Selected ", "onItemSelected: "+ year);
                repository.getFeeInfor(studentLocal.getStudentId(),year, semester);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setSpinnerYearValue(){
        List<StudyingYear> yearList = new ArrayList<StudyingYear>();
        for(int i = 2019; i <= 2022; i++) // create year from 2018 to 2025
            yearList.add(new StudyingYear(i));
        ArrayAdapter<StudyingYear> adapter = new ArrayAdapter<StudyingYear>(context, android.R.layout.simple_spinner_dropdown_item, new ArrayList<StudyingYear>(yearList));
        spinnerYear.setAdapter(adapter);

        spinnerYear.setSelection(2);
    }

    public void loadFee(ArrayList<FeeInfor> feeInfors, View root){

       // ArrayList<FeeInfor> listfee =new ArrayList<FeeInfor>(feeInfors);
        feeAdapter =new FeeAdapter(feeInfors);
        recyclerView.setAdapter(feeAdapter);
        LinearLayoutManager layout= new LinearLayoutManager(root.getContext());
        layout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layout);

        double totalFee=0;
        int numSubject=0;
        for (FeeInfor i:feeInfors) {
            totalFee += i.getTotalFee();
            numSubject++;
        }
        tv_totalfee.setText(totalFee+"VND");
        tv_numSubject.setText(numSubject+"");
    }
}
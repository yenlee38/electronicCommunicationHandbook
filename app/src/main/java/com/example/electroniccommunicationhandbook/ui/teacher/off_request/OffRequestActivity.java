package com.example.electroniccommunicationhandbook.ui.teacher.off_request;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.OffRequest;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.repository.AnnouncementRepository;
import com.example.electroniccommunicationhandbook.repository.ClassRepository;
import com.example.electroniccommunicationhandbook.repository.OffRequestRepository;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class OffRequestActivity extends AppCompatActivity {

    private OffRequestRepository requestRepository;
    private ClassRepository classRepository;
    private AppCompatButton btn_senRequest;
    private UserLocalStore userLocalStore;
    private Spinner spinner;
    private Teacher teacher;
    private Context context;
    private AppCompatEditText edtPickdate, edtReason;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off_request);
        btn_senRequest= findViewById(R.id.btn_send_off_request);
        spinner= findViewById(R.id.spinnerClass);
        edtPickdate= findViewById(R.id.edtpickDate);
        edtReason= findViewById(R.id.edtreason);
        context= this.getApplicationContext();
        userLocalStore= new UserLocalStore(this.getApplicationContext());

        requestRepository= OffRequestRepository.getInstance();
        if(userLocalStore.getRoleLocal()==1){
            teacher= userLocalStore.getTeacherLocal();
        }
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month= calendar.get(Calendar.MONTH);
        int semester=1;
        if(month>7) semester=1;
        if(month<7) {
            year= year-1;
            semester=2;
        }

        ArrayList<String> loading= new ArrayList<>();
        loading.add("...loading");
        initSpinner(loading);
        classRepository= ViewModelProviders.of(this).get(ClassRepository.class);
        classRepository.getClassOfTeacher(teacher.getTeacherID(),2021,1);
        observeClassListSpinner();

        btn_senRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class mclass= new Class();
                mclass =(Class) spinner.getSelectedItem();
                mclass.setClassId("CP0X36C5");
                if(mclass!=null)
                {
                    teacher.setTeacherID("Teacher12");
                    OffRequest offRequest= new OffRequest(Calendar.getInstance().getTime(),edtReason.getText().toString(),teacher,mclass);
                    offRequest= requestRepository.save(offRequest);
                    if(offRequest!=null)
                    {
                        Toast.makeText(context ,"Success",Toast.LENGTH_SHORT).show();
                    }
                    else  Toast.makeText(context ,"Fail",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void initSpinner(ArrayList<String> arrayList){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arrayList);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
    }

    void observeClassListSpinner(){
        classRepository.getClassList().observe(this, new Observer<ArrayList<Class>>() {
            @Override
            public void onChanged(ArrayList<Class> classes) {
                if(classes!=null && classes.size()>0)
                {
                    ArrayAdapter<Class> adapter = new ArrayAdapter<Class>(context, android.R.layout.simple_spinner_item,classes);
                    spinner.setAdapter(adapter);
                }
                else {
                    ArrayList<String> nofind= new ArrayList<>();
                    nofind.add("Can't find class!");
                    initSpinner(nofind);
                }

            }
        });
    }
}
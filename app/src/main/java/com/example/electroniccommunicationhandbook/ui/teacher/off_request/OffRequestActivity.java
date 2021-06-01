package com.example.electroniccommunicationhandbook.ui.teacher.off_request;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electroniccommunicationhandbook.MainActivity_parent;
import com.example.electroniccommunicationhandbook.MainActivity_teacher;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.common.SetDate;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.OffRequest;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.repository.AnnouncementRepository;
import com.example.electroniccommunicationhandbook.repository.ClassRepository;
import com.example.electroniccommunicationhandbook.repository.OffRequestRepository;
import com.example.electroniccommunicationhandbook.ui.authentication.login.Login;
import com.example.electroniccommunicationhandbook.ui.profile.UpdateSuccessfullyActivity;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class OffRequestActivity extends AppCompatActivity {

    private OffRequestRepository requestRepository;
    private ClassRepository classRepository;
    private AppCompatButton btn_senRequest, btn_back;
    private UserLocalStore userLocalStore;
    private Spinner spinner;
    private Teacher teacher;
    private Context context;
    private AppCompatEditText edtReason;
    private TextView  edtPickdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off_request);
        btn_senRequest= findViewById(R.id.btn_send_off_request);
        spinner= findViewById(R.id.spinnerClass);
        edtPickdate= findViewById(R.id.edtpickDate);
        edtReason= findViewById(R.id.edtreason);
        btn_back= findViewById(R.id.btn_comeback);
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
                if(checkInput()){
                    Class mclass= new Class();
                    mclass =(Class) spinner.getSelectedItem();
                    if(mclass!=null)
                    {
                        OffRequest offRequest= new OffRequest(Calendar.getInstance().getTime(),edtReason.getText().toString(),teacher,mclass);
                        offRequest= requestRepository.save(offRequest);
                        if(offRequest!=null)
                        {
                            Intent intent= new Intent(getApplicationContext(), UpdateSuccessfullyActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("Title", "Send request successfully");
                            bundle.putString("Detail","Please wait for the notification to accept the request from the school.");
                            bundle.putString("Key","1");
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else  Toast.makeText(context ,"Fail",Toast.LENGTH_SHORT).show();

                    }
                }
                else {
                    Toast.makeText(context ,"You have to fill all field !",Toast.LENGTH_SHORT).show();
                }

            }
        });

        edtPickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                Calendar cal = Calendar.getInstance();
                DateFormat formater= new SimpleDateFormat("E, MMM dd yyyy");
                //if the date is selected
                if (!edtPickdate.getText().toString().isEmpty())
                {
                    SimpleDateFormat simpFormat = new SimpleDateFormat("E, MMM dd yyyy");
                    //Calendar cal = Calendar.getInstance();
                    try {
                        cal.setTime(simpFormat.parse(edtPickdate.getText().toString()));
                        year = (cal.get(Calendar.YEAR));
                        month = cal.get(Calendar.MONTH);
                        day = cal.get(Calendar.DAY_OF_MONTH);
                    }
                    catch(Exception e)
                    {}
                }

                MaterialDatePicker datePicker =
                        MaterialDatePicker.Builder.datePicker()
                                .setTitleText("Select date")
                                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                                .build();

                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selectedDate) {
                        // user has selected a date
                        // format the date and set the text of the input box to be the selected date
                        // right now this format is hard-coded, this will change
                        ;
                        // Get the offset from our timezone and UTC.
                        TimeZone timeZoneUTC = TimeZone.getDefault();
                        // It will be negative, so that's the -1
                        int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;

                        // Create a date format, then a date object with our offset
                        SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                        Date date = new Date(selectedDate + offsetFromUTC);

                        edtPickdate.setText(simpleFormat.format(date));
                    }
                });

                   datePicker.show(getSupportFragmentManager(), "tag");



            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                if(userLocalStore.getRoleLocal()==1)
               {
                    Intent intent= new Intent(getApplicationContext(), MainActivity_teacher.class);
                    startActivity(intent);
                }
                else
               {
                    Intent intent= new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
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

    Boolean checkInput(){
        if(edtReason.getText().toString().isEmpty() || edtPickdate.getText().toString().isEmpty())
            return false;
        return true;
    }
}
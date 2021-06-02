package com.example.electroniccommunicationhandbook.ui.teacher.notification;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.common.StudyingYear;
import com.example.electroniccommunicationhandbook.entity.Announcement;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.OffRequest;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.repository.AnnouncementRepository;
import com.example.electroniccommunicationhandbook.repository.ClassRepository;
import com.example.electroniccommunicationhandbook.repository.PointRepository;
import com.example.electroniccommunicationhandbook.service.ClassService;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class NotificationFragment extends Fragment {

    private AppCompatSpinner spinnerClass;
    private AppCompatButton btnSend;
    private AppCompatEditText edtTitle, edtContent;
    private ClassRepository classRepository;
    private AnnouncementRepository announcementRepository;
    private Context context;
    private UserLocalStore userLocalStore;
    private Teacher teacher;
    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerClass= view.findViewById(R.id.spinnerClass);
        edtContent= view.findViewById(R.id.edtContent);
        edtTitle= view.findViewById(R.id.edtTitle);
        btnSend= view.findViewById(R.id.btn_send_notification);

        context= this.getContext();
        userLocalStore= new UserLocalStore(context);
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
        announcementRepository=ViewModelProviders.of(this).get(AnnouncementRepository.class);
        classRepository= ViewModelProviders.of(this).get(ClassRepository.class);
        classRepository.getClassOfTeacher(teacher.getTeacherID(),2021,1);
        observeClassListSpinner();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput())
                {
                    Class mclass= new Class();
                    mclass =(Class) spinnerClass.getSelectedItem();
                    if(mclass!=null)
                    {
                        Announcement announcement= new Announcement(teacher,mclass,edtContent.getText().toString(),Calendar.getInstance().getTime(), edtTitle.getText().toString());
                        announcement= announcementRepository.save(announcement);
                        if(announcement!=null){
                            activityCallback.onButtonClick("Notification has been sent successfully");
                        }
                        else  Toast.makeText(context ,"Fail",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(context ,"Loading class failed so you can not create announcement! Please reload!",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(context ,"You have to fill all field!",Toast.LENGTH_SHORT).show();

            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_notification_creation, container, false);

        return root;
    }

    Boolean checkInput(){
        if(edtTitle.getText().toString().isEmpty()|| edtContent.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }

    public void initSpinner(ArrayList<String> arrayList){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,arrayList);
        spinnerClass.setAdapter(adapter);
        spinnerClass.setSelection(0);
    }

    void observeClassListSpinner(){
        classRepository.getClassList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Class>>() {
            @Override
            public void onChanged(ArrayList<Class> classes) {
                if(classes!=null && classes.size()>0)
                {
                    ArrayAdapter<Class> adapter = new ArrayAdapter<Class>(context, android.R.layout.simple_spinner_item,classes);
                    spinnerClass.setAdapter(adapter);
                }
                else {
                    ArrayList<String> nofind= new ArrayList<>();
                    nofind.add("Can't find class!");
                    initSpinner(nofind);
                }

            }
        });
    }

    FirstFragmentListener activityCallback;
    public interface FirstFragmentListener {
        public void onButtonClick(String title);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityCallback = (FirstFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " You must implement FirstFragmentListener");
        }
    }



}
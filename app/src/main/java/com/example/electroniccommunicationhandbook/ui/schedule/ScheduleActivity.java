package com.example.electroniccommunicationhandbook.ui.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Class;

import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    private List<Class> mClassOfDay = null; // schedule for each day
    private RecyclerView rlv_schedule;
    private ScheduleAdapter scheduleAdapter;
    private ImageView img_back;

    public ScheduleActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

    }

    private void setRecyclerView(){
        scheduleAdapter = new ScheduleAdapter(mClassOfDay);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void initView(){
        rlv_schedule = findViewById(R.id.rlv_schedule);
        img_back = findViewById(R.id.img_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }




    private void sortClassBySchoolTime(List<Class> lClass){ // Sắp xếp lại thứ tự tiết học
        for(int i = 0; i < lClass.size() - 1; i++)
           for(int j = i + 1; j < lClass.size(); j++)
               if(lClass.get(j).getStartingSchoolTime() < lClass.get(i).getStartingSchoolTime())
                   swapClass(lClass.get(i), lClass.get(j));
    }

    private void swapClass(Class c1, Class c2){
        Class temp = new Class(c1);
        c1 = new Class(c2);
        c2 = new Class(temp);
    }

    private int isClassOfDay(Class classNow, int dayOfWeek){ // Kiểm tra class đó có phải là thứ trong tuần mà mình cần tìm hay không

        if(classNow.getDayOfWeek() == dayOfWeek)
            return 1;
        return 0;
    }
}
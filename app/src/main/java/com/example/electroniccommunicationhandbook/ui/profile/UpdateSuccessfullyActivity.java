package com.example.electroniccommunicationhandbook.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.MainActivity_teacher;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

public class UpdateSuccessfullyActivity extends AppCompatActivity {
    private AppCompatButton btnGoToHome;
    private UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_successfully);
        btnGoToHome = findViewById(R.id.btn_go_to_home);
        userLocalStore =new UserLocalStore(this);
        btnGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userLocalStore.getRoleLocal()==1){
                    Intent intentTeacher = new Intent(UpdateSuccessfullyActivity.this, MainActivity_teacher.class);
                    startActivity(intentTeacher);
                }else if(userLocalStore.getRoleLocal()==2){
                    Intent intentStudent = new Intent(UpdateSuccessfullyActivity.this, MainActivity.class);
                    startActivity(intentStudent);
                }else{
                    Intent intentParent = new Intent(UpdateSuccessfullyActivity.this, MainActivity.class);
                    startActivity(intentParent);
                }
            }
        });


    }


}
package com.example.electroniccommunicationhandbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.ui.authentication.login.Login;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

public class MainActivity_parent extends AppCompatActivity {
    Button btnLogout;
    TextView tv_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parent);

        initial();
        initEvent();
        getInfo();
    }
    public void getInfo(){
        UserLocalStore userLocalStore;
        userLocalStore = new UserLocalStore(getApplicationContext());
        Student student = new Student();
        try{ student = userLocalStore.getStudentLocal();
            tv_username.setText(student.getName());}
        catch (Exception ex){}
    }

    public void initial(){
        btnLogout= findViewById(R.id.btn_logout_parent);
        tv_username= findViewById(R.id.tv_username_parent);
    }
    public void initEvent(){
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserLocalStore userLocalStore= new UserLocalStore(getApplicationContext());
                userLocalStore.resetUserLocal();
                Intent intent= new Intent(MainActivity_parent.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
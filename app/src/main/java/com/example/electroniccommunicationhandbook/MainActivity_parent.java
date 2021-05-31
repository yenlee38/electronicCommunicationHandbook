package com.example.electroniccommunicationhandbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.electroniccommunicationhandbook.ui.authentication.login.Login;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

public class MainActivity_parent extends AppCompatActivity {
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parent);

        initial();
        initEvent();
    }
    public void initial(){
        btnLogout= findViewById(R.id.btn_logout_parent);
    }
    public void initEvent(){
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserLocalStore userLocalStore= new UserLocalStore(getApplicationContext());
                userLocalStore.resetUserLocal();
                Intent intent= new Intent(MainActivity_parent.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
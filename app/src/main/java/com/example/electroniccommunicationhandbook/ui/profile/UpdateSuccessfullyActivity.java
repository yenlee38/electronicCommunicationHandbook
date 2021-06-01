package com.example.electroniccommunicationhandbook.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.MainActivity_teacher;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.ui.authentication.login.Login;

public class UpdateSuccessfullyActivity extends AppCompatActivity {

    private TextView tvTitle,tvDetail;
    private String title, detail, key="";
    private Button btnGotohome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_successfully);
        tvDetail= findViewById(R.id.tv_detail);
        tvTitle= findViewById(R.id.tv_title);
        btnGotohome= findViewById(R.id.btn_go_to_home);
        Bundle bundle = getIntent().getExtras();
        this.title ="Update profile successfully";
        this.detail="";

        //from request
        if(bundle.getString("Key")!=null) {
            key= bundle.getString("Key");
            if (bundle.getString("Key").equals("1")) {
                this.title=bundle.getString("Title");
                this.detail= bundle.getString("Detail");
            }//from announcement
            else if(bundle.getString("Key").equals("2"))
            {
                this.title=bundle.getString("Title");
            }
        }
        tvTitle.setText(title);
        tvDetail.setText(detail);

        btnGotohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!key.equals("")){
                    if(key.equals("1") || key.equals("2"))
                    {
                        Intent intent= new Intent(getApplicationContext(), MainActivity_teacher.class);
                        startActivity(intent);
                    }
                }
            }
        });


    }
}
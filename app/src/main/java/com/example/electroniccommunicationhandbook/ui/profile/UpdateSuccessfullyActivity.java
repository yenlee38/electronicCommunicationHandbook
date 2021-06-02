package com.example.electroniccommunicationhandbook.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.MainActivity_parent;
import com.example.electroniccommunicationhandbook.MainActivity_teacher;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.ui.teacher.notification.NotificationFragment;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

public class UpdateSuccessfullyActivity extends AppCompatActivity {
    private AppCompatButton btnGoToHome;
    private UserLocalStore userLocalStore;
    private TextView tvTitle,tvDetail;
    private String title, detail, key="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_successfully);
        btnGoToHome = findViewById(R.id.btn_go_to_home);
        userLocalStore =new UserLocalStore(this);

        tvDetail= findViewById(R.id.tv_detail);
        tvTitle= findViewById(R.id.tv_title);
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

//        btnGotohome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!key.equals("")){
//                    if(key.equals("1") || key.equals("2"))
//                    {
//                        Intent intent= new Intent(getApplicationContext(), MainActivity_teacher.class);
//                        startActivity(intent);
//                    }
//                }
//            }
//        });

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
                    Intent intentParent = new Intent(UpdateSuccessfullyActivity.this, MainActivity_parent.class);
                    startActivity(intentParent);
                }
            }
        });


    }


}
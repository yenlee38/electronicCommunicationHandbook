package com.example.electroniccommunicationhandbook.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.MainActivity_parent;
import com.example.electroniccommunicationhandbook.MainActivity_teacher;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Parent;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.repository.PointRepository;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

public class ProfileActivity extends AppCompatActivity {
    private int role;
    private UserLocalStore userLocalStore;
    private ImageView imvBack,imvPhoto;
    private TextView tvID,tvName;
    private AppCompatButton btnUpdateProfile;
    private EditText edtEmail,edtPhone, edtAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Khởi tạo và ánh xạ các biến
        userLocalStore = new UserLocalStore(this);
        imvBack = findViewById(R.id.imv_back);
        imvPhoto = findViewById(R.id.imv_photo);
        tvID = findViewById(R.id.tv_student_id);
        tvName = findViewById(R.id.tv_name);
        btnUpdateProfile = findViewById(R.id.btn_update_profile);
        edtEmail =findViewById(R.id.edit_text_email_profile);
        edtPhone = findViewById(R.id.edit_text_phone_profile);
        edtAddress = findViewById(R.id.edit_text_address_profile);

        loadData();

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userLocalStore.getRoleLocal()==1){
                    Intent intentTeacher = new Intent(ProfileActivity.this, MainActivity_teacher.class);
                    startActivity(intentTeacher);
                }else if(userLocalStore.getRoleLocal()==2){
                    Intent intentStudent = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(intentStudent);
                }else{
                    Intent intentParent = new Intent(ProfileActivity.this, MainActivity_parent.class);
                    startActivity(intentParent);
                }
            }
        });

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, UpdateProfileActivity.class);
                startActivity(intent);
            }
        });
    }

// Hàm load data
    private void loadData() {
        // Load thông tin Giáo viên nếu role =1
        if(userLocalStore.getRoleLocal()==1){
            Teacher teacher = userLocalStore.getTeacherLocal();
            tvName.setText(teacher.getName());
            tvID.setText(teacher.getTeacherID());
            edtEmail.setText(teacher.getEmail());
            edtPhone.setText(teacher.getPhone());
            edtAddress.setText(teacher.getAddress());
//            Load thông tin Sinh viên nếu role =2
        }else if (userLocalStore.getRoleLocal()==2){
            Student student = userLocalStore.getStudentLocal();
            tvName.setText(student.getName());
            tvID.setText(student.getStudentId());
            edtEmail.setText(student.getEmail());
            edtPhone.setText(student.getPhone());
            edtAddress.setText(student.getAddress());
        }else{
            Parent parent = userLocalStore.getParentLocal();//Load thong tin phụ huynh
            tvName.setText(parent.getName());
            tvID.setText(parent.getParentId());
            edtEmail.setText(parent.getEmail());
            edtPhone.setText(parent.getPhone());
            edtAddress.setText(parent.getAddress());
        }

    }


}
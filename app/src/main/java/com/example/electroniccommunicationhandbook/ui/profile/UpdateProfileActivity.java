package com.example.electroniccommunicationhandbook.ui.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Parent;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class UpdateProfileActivity extends AppCompatActivity {
    private UserLocalStore userLocalStore;
    ImageView imvBack;
    EditText edtPhone,edtAddress;
    AppCompatButton btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        userLocalStore = new UserLocalStore(this);
        LoadData();
        final ProgressBar progressBar = findViewById(R.id.progess_bar);

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateProfileActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String numberPhone = edtPhone.getText().toString();
                String address = edtAddress.getText().toString();
                if(!Patterns.PHONE.matcher(numberPhone).matches()){ //Kiểm tra định dạng số điện thoại
                    createDialog("The number phone is not valid!","Notification");
                }else if(CheckTheChange(numberPhone,address)==false){//Kiểm tra thông tin có thay đổi hay không
                    createDialog("No change of information !","Notification");
                }
                else{
                    // lệnh lấy chuỗi mới của sdt bỏ kí tự đầu
                    String numberPhoneToGetOTP = numberPhone.substring(1,numberPhone.length());

                    btnUpdate.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    // Khởi tạo firebaseAuth
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    //Hàm để lấy sdt
                    PhoneAuthOptions phoneAuthOptions = PhoneAuthOptions.newBuilder(mAuth)
                            .setPhoneNumber("+84"+numberPhoneToGetOTP)
                            .setTimeout(60L, TimeUnit.SECONDS)//xét thời gian time out của SMS
                            .setActivity(UpdateProfileActivity.this)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                                //Hàm gửi code qua bên OTP Verify
                                @Override
                                public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    btnUpdate.setVisibility(View.GONE);
                                    Intent intent = new Intent(UpdateProfileActivity.this,VerifyOTPActivity.class);
                                    intent.putExtra("mobile",numberPhone);
                                    intent.putExtra("address",address);
                                    intent.putExtra("verificationId",verificationId);
                                    startActivity(intent);
                                }

                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    btnUpdate.setVisibility(View.GONE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    btnUpdate.setVisibility(View.GONE);
                                    createDialog("Update failed information !","Notification");
                                }
                            }).build();
                    PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);
                }

            }
        });

    }
    //Chương trình kiểm tra thay đổi của thông tin
    public boolean CheckTheChange(String inputPhone, String inputAddress){

        if(userLocalStore.getRoleLocal()==1)
        {
            Teacher teacher = userLocalStore.getTeacherLocal();
            if(!TextUtils.equals(inputPhone,teacher.getPhone()) || !TextUtils.equals(inputAddress,teacher.getAddress()) ){
                return true;
            }else
                return false;
        }else if(userLocalStore.getRoleLocal() ==2){
            Student student = userLocalStore.getStudentLocal();
            if(!TextUtils.equals(inputPhone,student.getPhone()) || !TextUtils.equals(inputAddress,student.getAddress())){
                return true;
            }else
                return false;
        }else{
            Parent parent = userLocalStore.getParentLocal();
            if(!TextUtils.equals(inputPhone,parent.getPhone()) || !TextUtils.equals(inputAddress,parent.getAddress())){
                return true;
            }else
                return false;
        }
    }

    public void LoadData(){
        imvBack = findViewById(R.id.imv_back);
        edtPhone = findViewById(R.id.edit_text_phone);
        edtAddress = findViewById(R.id.edit_text_address);
        btnUpdate = findViewById(R.id.btn_update_profile);
        if(userLocalStore.getRoleLocal()==1)
        {
            Teacher teacher = userLocalStore.getTeacherLocal();
            edtPhone.setText(teacher.getPhone());
            edtAddress.setText(teacher.getAddress());
        }else if(userLocalStore.getRoleLocal() ==2){
            Student student = userLocalStore.getStudentLocal();
            edtPhone.setText(student.getPhone());
            edtAddress.setText(student.getAddress());
        }else{
            Parent parent = userLocalStore.getParentLocal();
            edtPhone.setText(parent.getPhone());
            edtAddress.setText(parent.getAddress());
        }

    }

    //Hàm tạo dialog
    public void createDialog(String text, String title){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setPositiveButton("OK",null)
                .setTitle(title)
                .setMessage(text)
                .create();
        alertDialog.show();
    }
}
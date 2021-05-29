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
    ImageView imvBack;
    EditText edtPhone,edtAddress;
    AppCompatButton btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        LoadData();
        final ProgressBar progressBar = findViewById(R.id.progess_bar);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberPhone = edtPhone.getText().toString();
                String address = edtAddress.getText().toString();
                if(!Patterns.PHONE.matcher(numberPhone).matches()){
                    createDialog("Số điện thoại không hợp lệ","Thông báo");
                }else{
                    String numberPhoneToGetOTP = numberPhone.substring(1,numberPhone.length());
                    btnUpdate.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);

                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    PhoneAuthOptions phoneAuthOptions = PhoneAuthOptions.newBuilder(mAuth)
                            .setPhoneNumber("+84"+numberPhoneToGetOTP)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(UpdateProfileActivity.this)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    progressBar.setVisibility(View.GONE);
                                    btnUpdate.setVisibility(View.VISIBLE);
                                    Intent intent = new Intent(UpdateProfileActivity.this,VerifyOTPActivity.class);
                                    intent.putExtra("mobile", numberPhoneToGetOTP);
                                    intent.putExtra("verificationId",verificationId);
                                    startActivity(intent);
                                }

                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBar.setVisibility(View.GONE);
                                    btnUpdate.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressBar.setVisibility(View.GONE);
                                    btnUpdate.setVisibility(View.VISIBLE);
                                    Toast.makeText(UpdateProfileActivity.this,numberPhoneToGetOTP,Toast.LENGTH_SHORT).show();
                                }
                            }).build();
                    PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);
                }

            }
        });

    }
    public void LoadData(){

        imvBack = findViewById(R.id.imv_back);
        edtPhone = findViewById(R.id.edit_text_phone);
        edtAddress = findViewById(R.id.edit_text_address);
        btnUpdate = findViewById(R.id.btn_update_profile);

    }
    public void createDialog(String text, String title){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setPositiveButton("OK",null)
                .setTitle(title)
                .setMessage(text)
                .create();
        alertDialog.show();
    }
}
package com.example.electroniccommunicationhandbook.ui.resetpassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Dummy.Role;
import com.example.electroniccommunicationhandbook.repository.UpdateRepository;
import com.example.electroniccommunicationhandbook.ui.authentication.login.Login;
import com.example.electroniccommunicationhandbook.ui.profile.VerifyOTPActivity;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class GetPhoneNumberActivity extends AppCompatActivity {
    private ImageView imvBack;
    private EditText edtPhoneNumber;
    private AppCompatButton btnSend;
    private UpdateRepository updateRepository;
    MutableLiveData<Role> roleLiveData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_get);
        imvBack = findViewById(R.id.imv_back);
        edtPhoneNumber = findViewById(R.id.edit_text_phone_number);
        final ProgressBar progressBar = findViewById(R.id.progess_bar);
        roleLiveData = new MutableLiveData<>();
        updateRepository = new UpdateRepository(getApplication());

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(GetPhoneNumberActivity.this, Login.class);
                startActivity(intent);
            }
        });

        btnSend = findViewById(R.id.btn_send_numberphone);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //checkPhoneNumber();
                String phoneNumber = edtPhoneNumber.getText().toString();

                if(updateRepository.CheckPhoneNumber(phoneNumber)<=0){
                    createDialog("The number phone does not exist !","Notification");
                }

                else {

                    String phoneNumberToSend = phoneNumber.substring(1,phoneNumber.length());

                    btnSend.setVisibility(View.GONE);
                    progressBar.setVisibility(View.INVISIBLE);

                    FirebaseAuth mAuth = FirebaseAuth.getInstance();

                    PhoneAuthOptions phoneAuthOptions = PhoneAuthOptions.newBuilder(mAuth)
                            .setPhoneNumber("+84"+phoneNumberToSend)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(GetPhoneNumberActivity.this)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                                @Override
                                public void onCodeSent(@NonNull String VerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    progressBar.setVisibility(View.GONE);
                                    btnSend.setVisibility(View.VISIBLE);
                                    Intent intent = new Intent(GetPhoneNumberActivity.this, PasswordResetVerificationActivity.class);
                                    startActivity(intent);
                                    intent.putExtra("phoneNumber",phoneNumber);
                                    intent.putExtra("VerificationResetId",VerificationId);
                                }

                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBar.setVisibility(View.GONE);
                                    btnSend.setVisibility(View.VISIBLE);

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressBar.setVisibility(View.GONE);
                                    btnSend.setVisibility(View.VISIBLE);
                                    createDialog(e.getMessage(),"Notification");
                                }
                            }).build();

                    PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);

                }
            }
        });
    }

    public void checkPhoneNumber(){
        final ProgressBar progressBar = findViewById(R.id.progess_bar);
        roleLiveData = new MutableLiveData<>();
        String phoneNumber = edtPhoneNumber.getText().toString();
        roleLiveData = updateRepository.CheckPhoneNumberLiveData(edtPhoneNumber.getText().toString());
        roleLiveData.observe(this, new Observer<Role>() {
            @Override
            public void onChanged(Role role) {
                if(role != null)
                if(role.getRole()<=0){
                    createDialog("The number phone does not exist !","Notification");
                    Log.e("not exit:", "phone: " + edtPhoneNumber.getText().toString() + "role: " + role.getRole()) ;
                }
                else {
                    Log.e("exit:", "phone: " + edtPhoneNumber.getText().toString() + "role: " + role.getRole());
                    String phoneNumberToSend = phoneNumber.substring(1,phoneNumber.length());
                    btnSend.setVisibility(View.GONE);
                    progressBar.setVisibility(View.INVISIBLE);

                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    PhoneAuthOptions phoneAuthOptions = PhoneAuthOptions.newBuilder(mAuth)
                            .setPhoneNumber("+84"+phoneNumberToSend)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(GetPhoneNumberActivity.this)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                                @Override
                                public void onCodeSent(@NonNull String VerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    btnSend.setVisibility(View.GONE);
                                    Intent intent = new Intent(GetPhoneNumberActivity.this, PasswordResetVerificationActivity.class);
                                    startActivity(intent);
                                    intent.putExtra("VerificationResetId",VerificationId);
                                }

                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    btnSend.setVisibility(View.GONE);

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    btnSend.setVisibility(View.GONE);
                                    createDialog(e.getMessage(),"Notification");
                                }
                            }).build();
                    PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);
                }
            }
        });
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
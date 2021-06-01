package com.example.electroniccommunicationhandbook.ui.resetpassword;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.repository.UpdateRepository;

public class ChangePasswordActivity extends AppCompatActivity {
    private UpdateRepository updateRepository;
    private EditText edtPass,edtConfirmPass;
    private AppCompatButton btnReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

         String phoneNumber = getIntent().getStringExtra("phoneNumber");

        updateRepository = new UpdateRepository(getApplication());
        edtPass = findViewById(R.id.edit_text_pass);
        edtConfirmPass =findViewById(R.id.edit_text_confirm);
        btnReset = findViewById(R.id.btn_reset_password);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = edtPass.getText().toString();
                String confirmPassword = edtConfirmPass.getText().toString();
                if(password.length()<6 ){
                    createDialog("Password is not long enough !","Notification");
                }
                else if(!TextUtils.equals(password,confirmPassword)){
                    createDialog("Password does no match !","Thông báo");
                }
                else{
                    updateRepository.ResetPassword(phoneNumber,password) ;
                    Intent intent = new Intent(ChangePasswordActivity.this,ResetPasswordSuccessfullyActivity.class);
                    startActivity(intent);
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
package com.example.electroniccommunicationhandbook.ui.resetpassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.ui.authentication.login.Login;

public class ResetPasswordSuccessfullyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_successfully);
        final AppCompatButton btnGoToLogin = findViewById(R.id.btn_go_to_login);
        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPasswordSuccessfullyActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
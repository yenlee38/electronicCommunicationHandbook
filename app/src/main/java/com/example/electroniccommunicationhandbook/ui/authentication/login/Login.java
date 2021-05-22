package com.example.electroniccommunicationhandbook.ui.authentication.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.Repository.MainRepository;
import com.example.electroniccommunicationhandbook.entity.Account;
import com.example.electroniccommunicationhandbook.entity.Dummy.jwt;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.electroniccommunicationhandbook.util.Comon.MY_PREFS_FILE;

public class Login extends AppCompatActivity {
    Button login;
    EditText edtUserName, edtPassword;

    private MainRepository mainRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mainRepository = mainRepository.getInstance();

        login= findViewById(R.id.btn_login);
        edtUserName= findViewById(R.id.edtUserName_Login);
        edtPassword= findViewById(R.id.edtPassword_Login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: check Type login in radio button


                Account account = new Account(edtUserName.getText().toString(),edtPassword.getText().toString());

                mainRepository.getAuthenticateService().login(account).enqueue(new Callback<jwt>() {
                    @Override
                    public void onResponse(Call<jwt> call, Response<jwt> auth) {
                        if(auth != null){
                            Log.e("token",auth.body().getJwt()  );
                            String token=auth.body().getJwt();

                            //Save token
                            SharedPreferences.Editor prefsEditor = getSharedPreferences(MY_PREFS_FILE, MODE_PRIVATE).edit();
                            prefsEditor.putString("token",token);
                            prefsEditor.commit();

                            mainRepository.setToken(token);
                            //Go login
                            Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<jwt> call, Throwable t) {
                        Log.e("Token", "Noooooooooooo");
                    }
                });
            }
        });
    }
}
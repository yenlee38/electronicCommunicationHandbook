package com.example.electroniccommunicationhandbook.ui.authentication.login;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;

import android.app.DatePickerDialog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioButton;

import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.MainActivity_parent;
import com.example.electroniccommunicationhandbook.MainActivity_teacher;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.repository.MainRepository;
import com.example.electroniccommunicationhandbook.entity.Account;
import com.example.electroniccommunicationhandbook.entity.Dummy.jwt;
import com.example.electroniccommunicationhandbook.entity.Parent;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.ui.resetpassword.GetPhoneNumberActivity;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.electroniccommunicationhandbook.util.Comon.MY_PREFS_FILE;
import static com.example.electroniccommunicationhandbook.util.UnixEpochDateTypeAdapter.getUnixEpochDateTypeAdapter;


import java.io.UnsupportedEncodingException;
import java.util.Date;

public class Login extends AppCompatActivity {
    int role;
    Button login;
    EditText edtUserName, edtPassword;
    TextView tvResetPassword;
    UserLocalStore userLocalStore;

    Context context;

    RadioButton radTeacher, radParent, radStudent;

    private MainRepository mainRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.context = this;
        role = 1;

        userLocalStore = new UserLocalStore(context);
        mainRepository = mainRepository.getInstance();

        login= findViewById(R.id.btn_login);
        edtUserName= findViewById(R.id.edtUserName_Login);
        edtPassword= findViewById(R.id.edtPassword_Login);
        radParent= findViewById(R.id.rad_log_parent);
        radTeacher=findViewById(R.id.rad_log_teacher);
        radStudent= findViewById(R.id.rad_log_student);
        tvResetPassword = findViewById(R.id.tv_log_direct_reset);
        tvResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, GetPhoneNumberActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: check Type login in radio button

                if (radTeacher.isChecked()) {
                    role = 1;

                } else if (radStudent.isChecked()) {
                    role = 2;

                } else {
                    role = 3;

                }

                userLocalStore.clearUser();
                userLocalStore.setRoleLocal(role);

                Account account = new Account(edtUserName.getText().toString(), edtPassword.getText().toString());

                mainRepository.getAuthenticateService().login(account, role).enqueue(new Callback<jwt>() {
                    @Override
                    public void onResponse(Call<jwt> call, Response<jwt> auth) {

                        if (auth.isSuccessful()) {
                            String token = auth.body().getJwt();
                            //Get part include user information in token
                            JWT parsedJWT = new JWT(token);
                            Claim subscriptionMetaData = parsedJWT.getClaim("userJsonBase64");
                            String parsedValue = subscriptionMetaData.asString();

                            //Save token
                            SharedPreferences.Editor prefsEditor = getSharedPreferences(MY_PREFS_FILE, MODE_PRIVATE).edit();
                            prefsEditor.putString("token", token);
                            prefsEditor.commit();

                            mainRepository.setToken(token);

                            // Receiving side
                            byte[] data = Base64.decode(parsedValue, Base64.DEFAULT);
                            try {
                                //Convert from base64 to String json
                                String text = new String(data, "UTF-8");
                                JSONObject jsonObject = new JSONObject(text);
                                //Convert Json to object
                                Gson gson = new GsonBuilder()
                                        .registerTypeAdapter(Date.class, getUnixEpochDateTypeAdapter()).create();
                                if (role == 1) {
                                    Teacher teacher = gson.fromJson(text, Teacher.class);
                                    userLocalStore.storeTeacher(teacher);
                                    //Go login
                                    Intent intent = new Intent(getApplicationContext(), MainActivity_teacher.class);
                                    startActivity(intent);
                                } else if (role == 2) {
                                    Student student = gson.fromJson(text, Student.class);
                                    //TODO: save to share preference
                                    userLocalStore.storeStudent(student);
                                    //Go login
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Parent parent = gson.fromJson(text, Parent.class);
                                    //TODO: save to share preference
                                    userLocalStore.storeParent(parent);
                                    //Go login
                                    Intent intent = new Intent(getApplicationContext(), MainActivity_parent.class);
                                    startActivity(intent);
                                }
                            } catch (UnsupportedEncodingException | JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            constraintLayout.setVisibility(View.INVISIBLE);
                            Toast.makeText(getBaseContext(), "Email or password is incorrect", Toast.LENGTH_LONG);
                            //Dialog dialog= new Dialog();
//                            dialog.setTitle("Email or password is invalid!!!");
//                            dialog.setCancelable(true);
//                            dialog.show();

                        }
                    }


                    @Override
                    public void onFailure(Call<jwt> call, Throwable t) {
                        //Toast.makeText(getApplicationContext(),"Problem happen!!!", Toast.LENGTH_LONG);
                        //Toast.makeText(getApplication().getApplicationContext(), "Email or password is incorrect", Toast.LENGTH_LONG);
//                        Dialog dialog= new Dialog(getApplication());
//                        dialog.setTitle("Error happen, Try again !!!");
//                        dialog.setCancelable(true);
//                        dialog.show();
                        constraintLayout.setVisibility(View.INVISIBLE);
                        Toast.makeText(getBaseContext(),"Problem happen!!!", Toast.LENGTH_LONG);

                    }
                });

            }
        });
    }
}
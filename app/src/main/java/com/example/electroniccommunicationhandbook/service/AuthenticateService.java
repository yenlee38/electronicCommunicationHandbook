package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.Account;
import com.example.electroniccommunicationhandbook.entity.Dummy.jwt;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenticateService {
    @POST("/login")
    Call<jwt> login(@Body Account account);
}

package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.Account;
import com.example.electroniccommunicationhandbook.entity.Dummy.jwt;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AuthenticateService {
    @POST("/login/{role}")
    Call<jwt> login(@Body Account account, @Path("role") int role);
}

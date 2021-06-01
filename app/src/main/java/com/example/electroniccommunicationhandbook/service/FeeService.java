package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.FeeperCredit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FeeService {
    @GET("/fee/{year}")
    Call<FeeperCredit> findByYear(@Path("year") int year);
}

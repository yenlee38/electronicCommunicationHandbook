package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.OffRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OffRequestService {

    @POST("/offRequest")
    Call<OffRequest> save(@Body OffRequest offRequest);
}

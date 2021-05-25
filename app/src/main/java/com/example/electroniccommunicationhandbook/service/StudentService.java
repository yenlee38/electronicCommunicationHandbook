package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.Student;

import kotlin.ParameterName;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StudentService {

    @GET("/student/{id}")
    Call<Student> getInfo(@Path("id") String id);
}

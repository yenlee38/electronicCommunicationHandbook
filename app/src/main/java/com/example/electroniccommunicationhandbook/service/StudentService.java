package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.SchoolTime;
import com.example.electroniccommunicationhandbook.entity.Student;

import java.util.ArrayList;
import java.util.List;

import kotlin.ParameterName;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StudentService {

    @GET("/student/{id}")
    Call<Student> getInfo(@Path("id") String id);

    @GET("/student_class/schedule/{id_student}/{year}/{semester}")
    Call<ArrayList<Class>> getSchedule(@Path("id_student") String id_student, @Path("year") int year, @Path("semester") int semester);

    @GET("/schooltime")
    Call<ArrayList<SchoolTime>> getListSchoolTime();

}

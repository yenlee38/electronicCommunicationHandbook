package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TeacherService {
    @GET("/student_class/schedule/{id_student}/{year}/{semester}")
    Call<ArrayList<Class>> getSchedule(@Path("id_student") String id_student, @Path("year") int year, @Path("semester") int semester);

    @GET("/class/student/{classId}")
    Call<ArrayList<Student>> getClasses(@Path("classId") String classId);

}

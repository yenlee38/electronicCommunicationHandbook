package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ClassService {

    @GET("/class/student/{classId}")
    Call<ArrayList<Student>> getStudentInClass(@Path("classId") String classId);


    @GET("/class/{teacherId}/{year}/{semester}")
    Call<ArrayList<Class>> getClassOfTeacher (@Path("teacherId") String teacherId, @Path("semester") int semester, @Path("year") int year);
}

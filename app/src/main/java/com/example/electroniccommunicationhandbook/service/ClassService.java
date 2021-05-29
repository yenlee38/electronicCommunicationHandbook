package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ClassService {
    @GET("/class/teacher/{teacherId}")
    Call<ArrayList<Class>> getClassOfTeacher(@Path("teacherId") String teacherId );

    @GET("/class/student/{classId}")
    Call<ArrayList<Student>> getStudentInClass(@Path("classId") String classId);
}
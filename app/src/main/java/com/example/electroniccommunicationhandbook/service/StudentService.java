package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.SchoolTime;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentService {

    @GET("/student/{id}")
    Call<Student> getInfo(@Path("id") String id);

    @GET("/student_class/schedule/{id_student}/{year}/{semester}")
    Call<ArrayList<Class>> getSchedule(@Path("id_student") String id_student, @Path("year") int year, @Path("semester") int semester);

    @GET("/schooltime")
    Call<ArrayList<SchoolTime>> getListSchoolTime();

    @GET("/student_class/get/{id_student}/{year}/{semester}")
    Call<ArrayList<Student_Class>> getRateList(@Path("id_student") String studentId, @Path("year") int year, @Path("semester") int semester);

    @PUT("/student_class/")
    Call<Student_Class> updateStudentClass(@Body Student_Class student_class);

    @PUT("/student_class/student/{id_student}/class/{id_class}")
    Call<Student_Class> updateStudentClassById(@Body Student_Class student_class, @Path("id_student") String studentId, @Path("id_class") String classId );

}


package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student_Class;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentClassService {

    @GET("/student_class/{id_student}/{id_class}")
    Call<Student_Class> getPointOfStudentById(@Path("id_student") String studentId, @Path("id_class") String classId );

    @PUT("/student_class")
    Call<Student_Class> updatePointOfStudent (@Body Student_Class studentClass);

    @POST("/student_class")
    Call<Student_Class> createPointInfo (@Body Student_Class studentClass);


    @GET("/student_class")
    Call<ArrayList<Student_Class>> findAll();

    @GET("/student_class/{id_student}/{id_class}")
    Call<Student_Class> findPointByClassAndStudentID(@Path("id_student") String id_student, @Path("id_class") String id_class);

    @GET("/student_class/schedule/{id_student}/{year}/{semester}")
    Call<List<Class>> findStudentClassEverySemester(@Path("id_student") String studentId, @Path("year") int year, @Path("semester") int semester);

    @GET("/student_class/{id_student}/{year}/{semester}")
    Call<ArrayList<Student_Class>> findByStudentIdAndYearAndSemester(@Path("id_student") String studentId, @Path("year") int year, @Path("semester") int semester);

    @GET("/class/teacher/{teacherId}")
    Call<ArrayList<Class>> findClassByIdTeacher(@Path("teacherId") String teacherId);
}

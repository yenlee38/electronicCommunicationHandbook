package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PointService {
    @GET("/student_class/{id_student}/{id_class}")
    Call<Student_Class> getPointOfStudentById(@Path("id_student") String studentId, @Path("id_class") String classId );

    @PUT("/student_class")
    Call<Student_Class> updatePointOfStudent (@Body Student_Class studentClass);

    @POST("/student_class")
    Call<Student_Class> createPointInfo (@Body Student_Class studentClass);

    @GET("/student/{id}")
    Call<Student> findStudent (@Path("id") String id);
}

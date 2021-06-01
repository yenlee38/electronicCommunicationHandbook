package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.Announcement;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AnnouncementService {

    @GET("/announcement/{id}")
    Call< ArrayList<Announcement>> findById(@Path("id") int id);

    @GET("/announcement/studentId/{studentId}")
    Call<ArrayList<Announcement>> findByStudentId(@Path("studentId") String studentId);

    @POST("/announcement")
    Call<Announcement> save(@Body Announcement announcement);

    @GET("/announcement/school/studentId/{studentId}")
    Call<ArrayList<Announcement>> findByStudentIdAndSenderNull(@Path("studentId") String studentId);

    @GET("/announcement/senderId/{senderId}")
    Call<ArrayList<Announcement>> findBySenderId(@Path("senderId") String senderId);
}

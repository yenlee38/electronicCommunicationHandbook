package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.Announcement;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AnnouncementService {

    @GET("/announcement/{id}")
    public ArrayList<Announcement> findById(@Path("id") int id);
}

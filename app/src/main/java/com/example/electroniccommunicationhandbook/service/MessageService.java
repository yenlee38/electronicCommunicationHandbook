package com.example.electroniccommunicationhandbook.service;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Query;

import com.example.electroniccommunicationhandbook.entity.Message;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MessageService {
    @GET("/message/sender/{id}")
    Call<ArrayList<Message>> getMessageByAccountID(@Path("id") int senderAccountID);

}

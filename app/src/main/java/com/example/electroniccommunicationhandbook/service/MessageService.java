package com.example.electroniccommunicationhandbook.service;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Query;

import com.example.electroniccommunicationhandbook.entity.Account;
import com.example.electroniccommunicationhandbook.entity.Message;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Teacher;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessageService {
    @GET("/message/sender/{id}")
    Call<ArrayList<Message>> getMessageBySenderAccountID(@Path("id") int senderAccountID);

    @GET("/message/{id_teacher}/teacher/{name}")
    Call<ArrayList<Student>> getAllStudentOfTeacherWithSimilarName(@Path("id_teacher") String teacherID,
                                                                   @Path("name") String studentName);

    @GET("/message/{id_student}/student/{name}")
    Call<ArrayList<Teacher>> getAllTeacherOfStudentWithSimilarName(@Path("id_student") String studentID,
                                                                   @Path("name") String teacherName);

    @GET("/message/accountId/{accountId}")
    Call<ArrayList<Message>> getAllMessageByAccountId(@Path("accountId") int text);

    @GET("/student")
    Call<ArrayList<Student>> getAllStudent();

    @GET("/teacher")
    Call<ArrayList<Teacher>> getAllTeacher();

    @GET("/student/accountId/{accountId}")
    Call<Student> getStudentByAccountId(@Path("accountId") int accountId);

    @GET("/teacher/accountId/{accountId}")
    Call<Teacher> getTeacherByAccountId(@Path("accountId") int accountId);

    @GET("/message/{senderAccountId}/{receiverAccountId}")
    Call<ArrayList<Message>> getMessagesBetweenUser(@Path("senderAccountId") int senderAccountId,
                                                    @Path("receiverAccountId") int receiverAccountId);

    @POST("/message")
    Call<Message> createMessage(@Body Message message);

    @GET("/account/{id}")
    Call<Account> getAccountById(@Path("id") int id);
}

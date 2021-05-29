package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.entity.Student_ConfirmationPaper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RequestPaperService {
    @GET("/studentconfirmationpaper/findbystudentid/{studentId}")
    Call<ArrayList<Student_ConfirmationPaper>> getConfirmationPapers(@Path("studentId") String studentId);

    @POST("/createonfirmationpaper")
    Call<Student_ConfirmationPaper> createNewConfirmation (@Body Student_ConfirmationPaper student_confirmationPaper);

}

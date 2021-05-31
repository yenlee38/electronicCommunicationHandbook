package com.example.electroniccommunicationhandbook.entity;

import androidx.room.TypeConverters;

import com.example.electroniccommunicationhandbook.database.Convert;

import java.util.Date;

public class StudentConfirmationPaperId {

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getRequiredTime() {
        return requiredTime;
    }

    public StudentConfirmationPaperId(String studentId, Date requiredTime, int confirmationPaperId) {
        this.studentId = studentId;
        this.requiredTime = requiredTime;
        this.confirmationPaperId = confirmationPaperId;
    }

    public void setRequiredTime(Date requiredTime) {
        this.requiredTime = requiredTime;
    }

    public int getConfirmationPaperId() {
        return confirmationPaperId;
    }

    public void setConfirmationPaperId(int confirmationPaperId) {
        this.confirmationPaperId = confirmationPaperId;
    }

    private String studentId;
    @TypeConverters(Convert.class)
    private Date requiredTime;

    private int confirmationPaperId;
    public StudentConfirmationPaperId(){

    }
}

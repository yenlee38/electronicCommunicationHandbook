package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "Student_ConfirmationPaper")
public class Student_ConfirmationPaper {
    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "requiredTime")
    private Date requiredTime;

    @ColumnInfo (name = "studentId") //foreign key with table Student
    private int studentId;

    @ColumnInfo (name = "confirmationPaperId") //foreign key with table ConfirmationPaper
    private int confirmationPaperId;

    @NonNull
    public Date getRequiredTime() {
        return requiredTime;
    }

    public void setRequiredTime(@NonNull Date requiredTime) {
        this.requiredTime = requiredTime;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getConfirmationPaperId() {
        return confirmationPaperId;
    }

    public void setConfirmationPaperId(int confirmationPaperId) {
        this.confirmationPaperId = confirmationPaperId;
    }

    public Student_ConfirmationPaper() {
    }
}

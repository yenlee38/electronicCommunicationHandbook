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
    @ColumnInfo (name = "RequiredTime")
    private Date RequiredTime;

    @ColumnInfo (name = "StudentID") //foreign key with table Student
    private String StudentID;

    @ColumnInfo (name = "ConfirmationPaperID") //foreign key with table ConfirmationPaper
    private int ConfirmationPaperID;

    @NonNull
    public Date getRequiredTime() {
        return RequiredTime;
    }

    public void setRequiredTime(@NonNull Date requiredTime) {
        RequiredTime = requiredTime;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public int getConfirmationPaperID() {
        return ConfirmationPaperID;
    }

    public void setConfirmationPaperID(int confirmationPaperID) {
        ConfirmationPaperID = confirmationPaperID;
    }

    public Student_ConfirmationPaper(@NonNull Date requiredTime, String studentID, int confirmationPaperID) {
        RequiredTime = requiredTime;
        StudentID = studentID;
        ConfirmationPaperID = confirmationPaperID;
    }

    public Student_ConfirmationPaper() {
    }
}

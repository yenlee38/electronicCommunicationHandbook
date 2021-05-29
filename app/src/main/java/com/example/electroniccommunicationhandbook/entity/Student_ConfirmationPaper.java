package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
//@Entity(primaryKeys = {"requiredTime","student.getStudentId","confirmationPaper.getConfirmationPaperID"})
public class Student_ConfirmationPaper {

    private Date requiredTime;
    private Student student;
    private ConfirmationPaper confirmationPaper;
//    private String student;
//    private String confirmationPaper;
    public Date getRequiredTime() {
        return requiredTime;
    }

    public void setRequiredTime(Date requiredTime) {

        this.requiredTime = requiredTime;
    }
    public Student getStudentId() {
        return student;
    }

    public void setStudentId(Student studentId) {
        this.student = studentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ConfirmationPaper getConfirmationPaper() {
        return confirmationPaper;
    }

    public void setConfirmationPaper(ConfirmationPaper confirmationPaper) {
        this.confirmationPaper = confirmationPaper;
    }

    public Student_ConfirmationPaper(Date requiredTime, Student student, ConfirmationPaper confirmationPaper) {
        this.requiredTime = requiredTime;
        this.student = student;
        this.confirmationPaper = confirmationPaper;
    }

    public Student_ConfirmationPaper() {
    }
}

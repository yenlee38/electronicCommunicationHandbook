package com.example.electroniccommunicationhandbook.entity;

import androidx.room.TypeConverters;

import com.example.electroniccommunicationhandbook.database.Convert;

import java.util.Date;

//@Entity(primaryKeys = {"requiredTime","student.getStudentId","confirmationPaper.getConfirmationPaperID"})
public class Student_ConfirmationPaper {
    private StudentConfirmationPaperId studentConfirmpaperId;

    private Date requiredTime;
    private Student student;
    private ConfirmationPaper confirmationPaper;

    //    private String student;
//    private String confirmationPaper;
    public Date getRequiredTime() {
        return requiredTime;
    }

    public void setRequiredTime(Date requiredTime) {
        this.studentConfirmpaperId.setRequiredTime(requiredTime);
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
        this.studentConfirmpaperId.setStudentId(student.getStudentId());
        this.student = student;
    }

    public ConfirmationPaper getConfirmationPaper() {
        return confirmationPaper;
    }

    public void setConfirmationPaper(ConfirmationPaper confirmationPaper) {
        this.confirmationPaper = confirmationPaper;
        this.studentConfirmpaperId.setConfirmationPaperId(confirmationPaper.getConfirmationPaperId());

    }

    public Student_ConfirmationPaper(Date requiredTime, Student student, ConfirmationPaper confirmationPaper) {
        this.requiredTime = requiredTime;
        this.student = student;
        this.confirmationPaper = confirmationPaper;

        this.studentConfirmpaperId =new StudentConfirmationPaperId();;
        this.studentConfirmpaperId.setConfirmationPaperId(confirmationPaper.getConfirmationPaperId());
        this.studentConfirmpaperId.setRequiredTime(requiredTime);
        this.studentConfirmpaperId.setStudentId(student.getStudentId());
    }
    public Student_ConfirmationPaper(){}

}

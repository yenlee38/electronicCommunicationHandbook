package com.example.electroniccommunicationhandbook.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Time;

import java.util.Date;

public class Student_Class implements Serializable {

    private  StudentClassId studentClassId;

    private String studentId;

    private String classId;

    private float middleMark;

    private float finalMark;

    @SerializedName("Rating")
    private int rating;

    @SerializedName("Comment")
    private String comment;

    private Date createDate;

    private Class _class;

    private  Student student;

    public Class get_class() {
        return _class;
    }

    public void set_class(Class _class) {
        this._class = _class;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @NonNull
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(@NonNull String studentId) {
        this.studentId = studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public float getMiddleMark() {
        return middleMark;
    }

    public void setMiddleMark(float middleMark) {
        this.middleMark = middleMark;
    }

    public float getFinalMark() {
        return finalMark;
    }

    public void setFinalMark(float finalMark) {
        this.finalMark = finalMark;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        createDate = createDate;
    }

    public StudentClassId getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(StudentClassId studentClassId) {
        this.studentClassId = studentClassId;
    }

    public Student_Class(@NonNull String studentId, String classId, float middleMark, float finalMark, int rating, String comment, Date createDate) {
        this.studentId = studentId;
        this.classId = classId;
        this.middleMark = middleMark;
        this.finalMark = finalMark;
        this.rating = rating;
        this.comment = comment;
        createDate = createDate;
    }

    public Student_Class() {

        this.studentId = "studentId";
        this.classId = "classId";
        this.middleMark = 5;
        this.finalMark = 10;
        this.rating = 2;
        this.comment = "comment";
        createDate = new Date();
    }
    public Student_Class(@NonNull String studentId, String classId,
                         float middleMark, float finalMark, int rating, String comment, Date createDate,
                         Student student) {
        this.studentId = studentId;
        this.classId = classId;
        this.middleMark = middleMark;
        this.finalMark = finalMark;
        this.rating = rating;
        this.comment = comment;
        this.student=student;
        createDate = createDate;
    }
}

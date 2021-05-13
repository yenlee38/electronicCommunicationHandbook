package com.example.electroniccommunicationhandbook.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "Student_Class")
public class Student_Class {

    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "StudentID") //foreign key with table Student
    private String StudentID;

    @ColumnInfo (name = "ClassID") //foreign key with table Class
    private String ClassID;

    @ColumnInfo (name = "MiddleMark")
    private float MiddleMark;

    @ColumnInfo (name = "FinalMark")
    private float FinalMark;

    @ColumnInfo (name = "Rating")
    private int Rating;

    @ColumnInfo (name = "Comment")
    private String Comment;

    @ColumnInfo (name = "CreateDate")
    private Date CreateDate;

    @NonNull
    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(@NonNull String studentID) {
        StudentID = studentID;
    }

    public String getClassID() {
        return ClassID;
    }

    public void setClassID(String classID) {
        ClassID = classID;
    }

    public float getMiddleMark() {
        return MiddleMark;
    }

    public void setMiddleMark(float middleMark) {
        MiddleMark = middleMark;
    }

    public float getFinalMark() {
        return FinalMark;
    }

    public void setFinalMark(float finalMark) {
        FinalMark = finalMark;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public Student_Class(@NonNull String studentID, String classID, float middleMark, float finalMark, int rating, String comment, Date createDate) {
        StudentID = studentID;
        ClassID = classID;
        MiddleMark = middleMark;
        FinalMark = finalMark;
        Rating = rating;
        Comment = comment;
        CreateDate = createDate;
    }

    public Student_Class() {
    }
}

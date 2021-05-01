package com.example.electroniccommunicationhandbook.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Student_Class")
public class Student_Class {

    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo (name = "studentId") //foreign key with table Student
    private int studentId;

    @ColumnInfo (name = "classId") //foreign key with table Class
    private int classId;

    @ColumnInfo (name = "middleMark")
    private float middleMark;

    @ColumnInfo (name = "finalMark")
    private float finalMark;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
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

    public Student_Class() {
    }
}

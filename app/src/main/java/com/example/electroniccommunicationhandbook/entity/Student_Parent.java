package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Student_Parent")
public class Student_Parent {

    private String StudentID; //foreign key with table Student

    private String ParentID;

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(@NonNull String studentID) {
        StudentID = studentID;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }

    public Student_Parent(@NonNull String studentID, String parentID) {
        StudentID = studentID;
        ParentID = parentID;
    }

    public Student_Parent() {
    }
}

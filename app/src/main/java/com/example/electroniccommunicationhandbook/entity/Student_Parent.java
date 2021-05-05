package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Student_Parent")
public class Student_Parent {
    public int getStudentId() {
        return studentId;
    }
    public  Student_Parent(){
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "studentId") //foreign key with table Student
    private int studentId;

    @ColumnInfo (name = "parentId") //foreign key with table Parent
    private int parentId;
}

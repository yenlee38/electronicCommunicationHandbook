package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Student_Parent")
public class Student_Parent {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo (name = "studentId")
    private int studentId;
}

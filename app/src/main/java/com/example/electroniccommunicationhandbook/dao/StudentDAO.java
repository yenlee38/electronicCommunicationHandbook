package com.example.electroniccommunicationhandbook.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.electroniccommunicationhandbook.entity.Student;


public interface StudentDAO {
    @Query("SELECT * FROM Student where AccountID = :accountid")
    Student getStudentByAccountID(int accountid);
}

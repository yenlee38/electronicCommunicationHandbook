package com.example.electroniccommunicationhandbook.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student_Class;

import java.util.List;

public interface Student_ClassDAO {

    void insert (Student_Class st);

    void update (Student_Class st);

    void delete (Student_Class st);


}

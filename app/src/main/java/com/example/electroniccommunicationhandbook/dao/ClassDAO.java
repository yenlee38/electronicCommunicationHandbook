package com.example.electroniccommunicationhandbook.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Subject;


public interface ClassDAO {

    void insertNewClass(Class classNew);

    void deleteClass(Class classDelete);
    void updateClass(Class classUpdate);






}

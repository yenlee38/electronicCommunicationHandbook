package com.example.electroniccommunicationhandbook.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Subject;

@Dao
public interface ClassDAO {

    @Insert
    void insertNewClass(Class classNew);

    @Delete
    void deleteClass(Class classDelete);

    @Update
    void updateClass(Class classUpdate);


    @Query("SELECT subjectName FROM Subject WHERE subjectId = :subjectId")
    String getNameSubjectById(int subjectId);

    @Query("SELECT * FROM Subject WHERE subjectId = :subjectId")
    Subject getSubjectById(int subjectId);


}

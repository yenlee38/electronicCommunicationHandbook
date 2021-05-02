package com.example.electroniccommunicationhandbook.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student_Class;

import java.util.List;

@Dao
public interface Student_ClassDAO {

    @Insert
    void insert (Student_Class st);

    @Update
    void update (Student_Class st);

    @Delete
    void delete (Student_Class st);

    @Query("SELECT * FROM Class, Student_Class WHERE Class.classId = Student_Class.classId" +
            " AND Student_Class.studentId = :studentId ")
    List<Class> getListClassByStudentId(int studentId); // lọc ra danh sách lớp học theo sinh viên


}

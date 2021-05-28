package com.example.electroniccommunicationhandbook.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.electroniccommunicationhandbook.entity.Account;
import com.example.electroniccommunicationhandbook.entity.Message;
import com.example.electroniccommunicationhandbook.entity.Student;

import java.util.List;

@Dao
public interface MessageDAO {
    @Insert
    void insert(Message message);

//    @Query("SELECT * FROM Message where senderAccount= :accountid")
//    List<Message> getAllMessageByAccount(Account accountid);
//
//    @Query ("SELECT * FROM student " +
//            " where student.StudentID IN (SELECT StudentID from student_class where classID  = :classID) " +
//            " and Name like '%'||:name||'%'")
//    List<Student> getAllStudentInClassBySimilarName (String classID, String name);
//
//    @Query ("SELECT * " +
//            " FROM student " +
//            " where student.StudentID IN (SELECT StudentID from student_class where classID " +
//            " IN (SELECT classID FROM class where teacherid = :teacherID)) " +
//            " and Name like '%'||:name||'%'")
//    List<Student> getAllStudentOfTeacherWithSimilarName (String teacherID, String name);
}

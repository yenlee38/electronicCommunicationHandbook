package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.util.Date;

@Entity (tableName = "Class")
public class Class {
    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "ClassID")
    private String ClassID;

    @ColumnInfo (name = "SubjectID") // foreign key
    private String SubjectID;

    @ColumnInfo (name = "TeacherID") // foreign key
    private String TeacherID;

    @ColumnInfo (name = "Semester")
    private int Semester;

    @ColumnInfo (name = "StudyingYear")
    private int StudyingYear;

    @ColumnInfo (name = "StartingDate")
    private Date StartingDate;

    @ColumnInfo (name = "EndDate")
    private Date EndDate;

    @ColumnInfo (name = "DayOfWeek")
    private int DayOfWeek;

    @ColumnInfo (name = "Room")
    private String Room;

    @ColumnInfo (name = "StartingSchoolTime") ///Tiết học
    private int StartingSchoolTime;

    @ColumnInfo (name = "EndSchoolTime")
    private int EndSchoolTime;

    @NonNull
    public String getClassID() {
        return ClassID;
    }

    public void setClassID(@NonNull String classID) {
        ClassID = classID;
    }

    public String getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(String subjectID) {
        SubjectID = subjectID;
    }

    public String getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(String teacherID) {
        TeacherID = teacherID;
    }

    public int getSemester() {
        return Semester;
    }

    public void setSemester(int semester) {
        Semester = semester;
    }

    public int getStudyingYear() {
        return StudyingYear;
    }

    public void setStudyingYear(int studyingYear) {
        StudyingYear = studyingYear;
    }

    public Date getStartingDate() {
        return StartingDate;
    }

    public void setStartingDate(Date startingDate) {
        StartingDate = startingDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public int getDayOfWeek() {
        return DayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        DayOfWeek = dayOfWeek;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public int getStartingSchoolTime() {
        return StartingSchoolTime;
    }

    public void setStartingSchoolTime(int startingSchoolTime) {
        StartingSchoolTime = startingSchoolTime;
    }

    public int getEndSchoolTime() {
        return EndSchoolTime;
    }

    public void setEndSchoolTime(int endSchoolTime) {
        EndSchoolTime = endSchoolTime;
    }

    public Class(@NonNull String classID, String subjectID, String teacherID, int semester, int studyingYear, Date startingDate, Date endDate, int dayOfWeek, String room, int startingSchoolTime, int endSchoolTime) {
        ClassID = classID;
        SubjectID = subjectID;
        TeacherID = teacherID;
        Semester = semester;
        StudyingYear = studyingYear;
        StartingDate = startingDate;
        EndDate = endDate;
        DayOfWeek = dayOfWeek;
        Room = room;
        StartingSchoolTime = startingSchoolTime;
        EndSchoolTime = endSchoolTime;
    }

    public Class(Class classNew){
        ClassID = classNew.ClassID;
        SubjectID = classNew.SubjectID;
        TeacherID = classNew.TeacherID;
        Semester = classNew.Semester;
        StudyingYear = classNew.StudyingYear;
        StartingDate = classNew.StartingDate;
        EndDate = classNew.EndDate;
        DayOfWeek = classNew.DayOfWeek;
        Room = classNew.Room;
        StartingSchoolTime = classNew.StartingSchoolTime;
        EndSchoolTime = classNew.EndSchoolTime;
    }

    public Class() {
    }


}

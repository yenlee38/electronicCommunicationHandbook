package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.util.Date;

@Entity (tableName = "Class")
public class Class {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo (name = "classId")
    private int classId;

    @ColumnInfo (name = "subjectId") // foreign key
    private int subjectId;

    @ColumnInfo (name = "teacherId") // foreign key
    private int teacherId;

    @ColumnInfo (name = "semester")
    private int semester;

    @ColumnInfo (name = "studyingYear")
    private int studyingYear;

    @ColumnInfo (name = "classStartDate")
    private Date classStartDate;

    @ColumnInfo (name = "classEndDate")
    private Date classEndDate;

    @ColumnInfo (name = "classDayOfWeek")
    private int classDayOfWeek;

    @ColumnInfo (name = "classRoom")
    private String classRoom;

    @ColumnInfo (name = "startSchoolTime") ///Tiết học
    private int startSchoolTime;

    @ColumnInfo (name = "endSchoolTime")
    private int endSchoolTime;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getStudyingYear() {
        return studyingYear;
    }

    public void setStudyingYear(int studyingYear) {
        this.studyingYear = studyingYear;
    }

    public Date getClassStartDate() {
        return classStartDate;
    }

    public void setClassStartDate(Date classStartDate) {
        this.classStartDate = classStartDate;
    }

    public Date getClassEndDate() {
        return classEndDate;
    }

    public void setClassEndDate(Date classEndDate) {
        this.classEndDate = classEndDate;
    }

    public int getClassDayOfWeek() {
        return classDayOfWeek;
    }

    public void setClassDayOfWeek(int classDayOfWeek) {
        this.classDayOfWeek = classDayOfWeek;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public int getStartSchoolTime() {
        return startSchoolTime;
    }

    public void setStartSchoolTime(int startSchoolTime) {
        this.startSchoolTime = startSchoolTime;
    }

    public int getEndSchoolTime() {
        return endSchoolTime;
    }

    public void setEndSchoolTime(int endSchoolTime) {
        this.endSchoolTime = endSchoolTime;
    }

    public Class() {
    }
}

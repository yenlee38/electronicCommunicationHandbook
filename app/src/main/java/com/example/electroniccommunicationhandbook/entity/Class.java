package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;

public class Class {

    private String classId;
    private Subject subject;
    private Teacher teacher;
    private int semester;
    private int studyingYear;
    private Date startingDate;
    private Date endDate;
    private int dayOfWeek;
    private String room;
    private int startingSchoolTime;
    private int endSchoolTime;
    private Collection<Announcement> announcements;
    private Collection<Student_Class>  students;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getStartingSchoolTime() {
        return startingSchoolTime;
    }

    public void setStartingSchoolTime(int startingSchoolTime) {
        this.startingSchoolTime = startingSchoolTime;
    }

    public int getEndSchoolTime() {
        return endSchoolTime;
    }

    public void setEndSchoolTime(int endSchoolTime) {
        this.endSchoolTime = endSchoolTime;
    }

    public Collection<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(Collection<Announcement> announcements) {
        this.announcements = announcements;
    }

    public Collection<Student_Class> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student_Class> students) {
        this.students = students;
    }

    public Class(String classId, Subject subject, Teacher teacher, int semester, int studyingYear, Date startingDate, Date endDate, int dayOfWeek, String room, int startingSchoolTime, int endSchoolTime, Collection<Announcement> announcements, Collection<Student_Class> students) {
        this.classId = classId;
        this.subject = subject;
        this.teacher = teacher;
        this.semester = semester;
        this.studyingYear = studyingYear;
        this.startingDate = startingDate;
        this.endDate = endDate;
        this.dayOfWeek = dayOfWeek;
        this.room = room;
        this.startingSchoolTime = startingSchoolTime;
        this.endSchoolTime = endSchoolTime;
        this.announcements = announcements;
        this.students = students;
    }

    public Class(Class classNew){
        classId = classNew.getClassId();
        subject = classNew.getSubject();
        teacher = classNew.getTeacher();
        semester = classNew.getSemester();
        studyingYear = classNew.getStudyingYear();
        startingDate = classNew.getStartingDate();
        endDate = classNew.getEndDate();
        dayOfWeek = classNew.getDayOfWeek();
        room = classNew.getRoom();
        startingSchoolTime = classNew.getStartingSchoolTime();
        endSchoolTime = classNew.getEndSchoolTime();
    }

    public Class() {
    }
}

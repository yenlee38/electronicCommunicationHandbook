package com.example.electroniccommunicationhandbook.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.Date;

public class Class {
    @SerializedName("class_id")
    private String classId;
    private Subject subject;
    private Teacher teacher;
    private int semester;
    private int studyingYear;
    private Date classEndDate;
    private Date classStartDate;
    private int classDayOfWeek;
    private String classRoom;
    private int startSchoolTime;
    private int endSchoolTime;
//    private Collection<Announcement> announcements;
  //  private Collection<Student_Class>  students;

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

    public Date getClassEndDate() {
        return classEndDate;
    }

    public void setClassEndDate(Date classEndDate) {
        this.classEndDate = classEndDate;
    }

    public Date getClassStartDate() {
        return classStartDate;
    }

    public void setClassStartDate(Date classStartDate) {
        this.classStartDate = classStartDate;
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

//    public Collection<Announcement> getAnnouncements() {
//        return announcements;
//    }
//
//    public void setAnnouncements(Collection<Announcement> announcements) {
//        this.announcements = announcements;
//    }

//    public Collection<Student_Class> getStudents() {
//        return students;
//    }
//
//    public void setStudents(Collection<Student_Class> students) {
//        this.students = students;
//    }

    public Class(String classId, Subject subject, Teacher teacher, int semester, int studyingYear, Date classEndDate, Date classStartDate, int classDayOfWeek, String classRoom, int startSchoolTime, int endSchoolTime, Collection<Announcement> announcements, Collection<Student_Class> students) {
        this.classId = classId;
        this.subject = subject;
        this.teacher = teacher;
        this.semester = semester;
        this.studyingYear = studyingYear;
        this.classEndDate = classEndDate;
        this.classStartDate = classStartDate;
        this.classDayOfWeek = classDayOfWeek;
        this.classRoom = classRoom;
        this.startSchoolTime = startSchoolTime;
        this.endSchoolTime = endSchoolTime;
//        this.announcements = announcements;
      // this.students = students;
    }

    public Class(Class classNew){
        classId = classNew.getClassId();
        subject = classNew.getSubject();
        teacher = classNew.getTeacher();
        semester = classNew.getSemester();
        studyingYear = classNew.getStudyingYear();
        classEndDate = classNew.getClassEndDate();
        classStartDate = classNew.getClassStartDate();
        classDayOfWeek = classNew.getClassDayOfWeek();
        classRoom = classNew.getClassRoom();
        startSchoolTime = classNew.getStartSchoolTime();
        endSchoolTime = classNew.getEndSchoolTime();
    }

    public Class() {
    }
}

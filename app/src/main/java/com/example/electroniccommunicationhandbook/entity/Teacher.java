package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "Teacher")
public class Teacher {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo (name = "teacherId")
    private int teacherId;

    @ColumnInfo (name = "teacherName")
    private String teacherName;

    @ColumnInfo (name = "teacherBirthday")
    private Date teacherBirthday;

    @ColumnInfo (name = "teacherDegree")
    private String teacherDegree;

    @ColumnInfo (name = "teacherAddress")
    private String teacherAddress;

    @ColumnInfo (name = "teacherEmail")
    private String teacherEmail;

    @ColumnInfo (name = "teacherImage")
    private String teacherImage;

    @ColumnInfo (name = "accountId") //foreign key with table Account
    private int accountId;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Date getTeacherBirthday() {
        return teacherBirthday;
    }

    public void setTeacherBirthday(Date teacherBirthday) {
        this.teacherBirthday = teacherBirthday;
    }

    public String getTeacherDegree() {
        return teacherDegree;
    }

    public void setTeacherDegree(String teacherDegree) {
        this.teacherDegree = teacherDegree;
    }

    public String getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherImage() {
        return teacherImage;
    }

    public void setTeacherImage(String teacherImage) {
        this.teacherImage = teacherImage;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Teacher(int accountId) {
        this.accountId = accountId;
    }

    public Teacher() {
    }
}

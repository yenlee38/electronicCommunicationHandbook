package com.example.electroniccommunicationhandbook.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Student")
public class Student {

    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo (name = "studentId")
    private int studentId;

    @ColumnInfo (name = "studentName")
    private String studentName;

    @ColumnInfo (name = "studentBirthday")
    private Date studentBirthday;

    @ColumnInfo (name = "studentPhone")
    private String studentPhone;

    @ColumnInfo (name = "studentAddress")
    private String studentAddress;

    @ColumnInfo (name = "studentEmail")
    private String studentEmail;

    @ColumnInfo (name = "studentImage")
    private String studentImage;

    @ColumnInfo (name = "studentCode") // mã số sv
    private String studentCode;

    @ColumnInfo (name = "accountId") //foreign key with table Account
    private int accountId;

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getStudentBirthday() {
        return studentBirthday;
    }

    public void setStudentBirthday(Date studentBirthday) {
        this.studentBirthday = studentBirthday;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(String studentImage) {
        this.studentImage = studentImage;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Student() {
    }
}

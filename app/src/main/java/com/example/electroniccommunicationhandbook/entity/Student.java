package com.example.electroniccommunicationhandbook.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.electroniccommunicationhandbook.database.Convert;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    @SerializedName("studentId")
    @Expose

    private String studentId;

    @SerializedName("studentName")
    @Expose
    private String name;


    @SerializedName("studentBirthday")
    @Expose

    @TypeConverters(Convert.class)
    private Date  birthday;

    @SerializedName("studentGender")
    @Expose
    private String gender;

    @SerializedName("studentPhone")
    @Expose
    private String phone;

    @SerializedName("studentAddress")
    @Expose
    private String address;

    @SerializedName("studentEmail")
    @Expose
    private String email;

    @SerializedName("studentImage")
    @Expose
    private String image;

    private String bankSeri;

    private Account account;

    private int year;

    private String major;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBankSeri() {
        return bankSeri;
    }

    public void setBankSeri(String bankSeri) {
        this.bankSeri = bankSeri;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Student(String studentId, String name, Date birthday, String gender, String phone, String address, String email, String image, String bankSeri, Account account) {
        this.studentId = studentId;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.image = image;
      //  this.bankSeri = bankSeri;
        this.account = account;
    }

    public Student() {
        this.year = 0;

    }

    public Student(String studentId, String name, Date birthday, String gender, String phone, String address,
                   String email, String image, String bankSeri, Account account, String year, String major) {
        this.studentId = studentId;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.image = image;
     //   this.bankSeri = bankSeri;
        this.account = account;
      //  this.year = year;
      //  this.major = major;
    }
}

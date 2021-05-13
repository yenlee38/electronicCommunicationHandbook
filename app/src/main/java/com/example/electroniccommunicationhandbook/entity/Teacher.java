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
    @ColumnInfo (name = "TeacherID")
    private String TeacherID;

    @ColumnInfo (name = "Name")
    private String Name;

    @ColumnInfo (name = "Birthday")
    private Date Birthday;

    @ColumnInfo (name = "Degree")
    private String Degree;

    @ColumnInfo (name = "Phone")
    private String Phone;

    @ColumnInfo (name = "Gender")
    private String Gender;

    @ColumnInfo (name = "Address")
    private String Address;

    @ColumnInfo (name = "Email")
    private String Email;

    @ColumnInfo (name = "Image")
    private String Image;

    @ColumnInfo (name = "AccountID") //foreign key with table Account
    private int AccountID;

    public String getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(String teacherID) {
        TeacherID = teacherID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int accountID) {
        AccountID = accountID;
    }

    public Teacher(String teacherID, String name, Date birthday, String degree, String phone, String gender, String address, String email, String image, int accountID) {
        TeacherID = teacherID;
        Name = name;
        Birthday = birthday;
        Degree = degree;
        Phone = phone;
        Gender = gender;
        Address = address;
        Email = email;
        Image = image;
        AccountID = accountID;
    }

    public Teacher() {
    }
}

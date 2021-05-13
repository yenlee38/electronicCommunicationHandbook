package com.example.electroniccommunicationhandbook.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Student")
public class Student {

    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "StudentID")
    private String StudentID;

    @ColumnInfo (name = "Name")
    private String Name;

    @ColumnInfo (name = "Birthday")
    private Date Birthday;

    @ColumnInfo (name = "Gender")
    private String Gender;

    @ColumnInfo (name = "Phone")
    private String Phone;

    @ColumnInfo (name = "Address")
    private String Address;

    @ColumnInfo (name = "Email")
    private String Email;

    @ColumnInfo (name = "Image")
    private String Image;

    @ColumnInfo (name = "BankSeri")
    private String BankSeri;

    @ColumnInfo (name = "AccountID") //foreign key with table Account
    private int AccountID;

    @NonNull
    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(@NonNull String studentID) {
        StudentID = studentID;
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

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
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

    public String getBankSeri() {
        return BankSeri;
    }

    public void setBankSeri(String bankSeri) {
        BankSeri = bankSeri;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int accountID) {
        AccountID = accountID;
    }

    public Student(@NonNull String studentID, String name, Date birthday, String gender, String phone, String address, String email, String image, String bankSeri, int accountID) {
        StudentID = studentID;
        Name = name;
        Birthday = birthday;
        Gender = gender;
        Phone = phone;
        Address = address;
        Email = email;
        Image = image;
        BankSeri = bankSeri;
        AccountID = accountID;
    }

    public Student() {
    }
}

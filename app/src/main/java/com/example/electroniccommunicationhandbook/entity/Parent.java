package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "Parent")
public class Parent {
    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "ParentID")
    private String ParentID;

    @ColumnInfo (name = "Name")
    private String Name;

    @ColumnInfo (name = "Birthday")
    private Date Birthday;

    @ColumnInfo (name = "Phone")
    private String Phone;

    @ColumnInfo (name = "Address")
    private String Address;

    @ColumnInfo (name = "Email")
    private String Email;

    @ColumnInfo (name = "Gender")
    private String Gender;

    @ColumnInfo (name = "Image")
    private String Image;

    @ColumnInfo (name = "AccountID") // foreign key with table Account
    private String AccountID;

    @NonNull
    public String getParentID() {
        return ParentID;
    }

    public void setParentID(@NonNull String parentID) {
        ParentID = parentID;
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

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String accountID) {
        AccountID = accountID;
    }

    public Parent(@NonNull String parentID, String name, Date birthday, String phone, String address, String email, String gender, String image, String accountID) {
        ParentID = parentID;
        Name = name;
        Birthday = birthday;
        Phone = phone;
        Address = address;
        Email = email;
        Gender = gender;
        Image = image;
        AccountID = accountID;
    }

    public Parent() {
    }


}

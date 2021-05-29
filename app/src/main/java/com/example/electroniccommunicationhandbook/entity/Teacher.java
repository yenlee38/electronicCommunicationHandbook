package com.example.electroniccommunicationhandbook.entity;

import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.example.electroniccommunicationhandbook.database.Convert;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class Teacher implements Serializable {


    private String teacherID;

    private String name;

    @TypeConverters(Convert.class)
    private Date birthday;

    private String degree;

    @SerializedName("teacherPhone")
    @Expose
    private String phone;

    private String gender;

    @SerializedName("teacherAddress")
    @Expose
    private String address;

    @SerializedName("teacherEmail")
    @Expose
    private String email;

    private String image;

    private Account account;

    private Collection<Announcement> announcements;

    private Collection<Class> classes;

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Collection<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(Collection<Announcement> announcements) {
        this.announcements = announcements;
    }

    public Collection<Class> getClasses() {
        return classes;
    }

    public void setClasses(Collection<Class> classes) {
        this.classes = classes;
    }


    public Teacher(String teacherID, String name, Date birthday, String degree, String phone, String gender, String address, String email, String image, Account account, Collection<Announcement> announcements, Collection<Class> classes) {
        this.teacherID = teacherID;

        this.name = name;
        this.birthday = birthday;
        this.degree = degree;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.image = image;
        this.account = account;
        this.announcements = announcements;
        this.classes = classes;
    }

    public Teacher() {
    }

    public Teacher(String teacherId, String name, Account account) {
        this.teacherID = teacherId;
        this.name = name;
        this.account = account;
    }
}

package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Announcement {

    @SerializedName("announcementID")
    @Expose
    private int announcementID;
    private Teacher sender;
    @SerializedName("_Class")
    @Expose
    private Class _Class;
    private String content;
    private Date announcementTime;
    private String title="";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAnnouncementId() {
        return announcementID;
    }

    public void setAnnouncementId(int announcementId) {
        this.announcementID = announcementId;
    }

    public Teacher getSender() {
        return sender;
    }

    public void setSender(Teacher sender) {
        this.sender = sender;
    }

    public Class get_class() {
        return _Class;
    }

    public void set_class(Class _class) {
        this._Class = _class;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAnnouncementTime() {
        return announcementTime;
    }

    public void setAnnouncementTime(Date announcementTime) {
        this.announcementTime = announcementTime;
    }

    public Announcement() {
    }

    public Announcement( Teacher sender, Class _class, String content, Date announcementTime, String title) {
        this.sender = sender;
        this._Class = _class;
        this.content = content;
        this.announcementTime = announcementTime;
        this.title= title;
    }
}

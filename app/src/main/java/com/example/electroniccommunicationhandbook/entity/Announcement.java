package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "Announcement")
public class Announcement {

    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo (name = "AnnouncementID")
    private int AnnouncementID;

    @ColumnInfo (name = "SenderID")
    private int SenderID;

    @ColumnInfo (name = "ClassID")
    private String ClassID;

    @ColumnInfo (name = "Content")
    private String Content;

    @ColumnInfo (name = "AnnouncementTime")
    private Date AnnouncementTime;

    public int getAnnouncementID() {
        return AnnouncementID;
    }

    public void setAnnouncementID(int announcementID) {
        AnnouncementID = announcementID;
    }

    public int getSenderID() {
        return SenderID;
    }

    public void setSenderID(int senderID) {
        SenderID = senderID;
    }

    public String getClassID() {
        return ClassID;
    }

    public void setClassID(String classID) {
        ClassID = classID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getAnnouncementTime() {
        return AnnouncementTime;
    }

    public void setAnnouncementTime(Date announcementTime) {
        AnnouncementTime = announcementTime;
    }

    public Announcement() {
    }
}

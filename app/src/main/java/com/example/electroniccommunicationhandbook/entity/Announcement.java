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
    @ColumnInfo (name = "announcementId")
    private int announcementId;

    @ColumnInfo (name = "announcementContent")
    private String announcementContent;

    @ColumnInfo (name = "announcementTime")
    private Date announcementTime;


    public int getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    public Date getAnnouncementTime() {
        return announcementTime;
    }

    public void setAnnouncementTime(Date announcementTime) {
        this.announcementTime = announcementTime;
    }

    public Announcement() {
    }
}

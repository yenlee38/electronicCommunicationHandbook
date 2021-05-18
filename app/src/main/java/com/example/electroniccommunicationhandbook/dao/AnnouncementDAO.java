package com.example.electroniccommunicationhandbook.dao;

import androidx.room.Dao;

import com.example.electroniccommunicationhandbook.entity.Announcement;

@Dao
public interface AnnouncementDAO {

    public void Insert(Announcement announcement);

}

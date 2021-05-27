package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;

public class SchoolTime {

    private int schoolTimeOrder;
    private String startingTime;
    private String endTime;

    public int getSchoolTimeOrder() {
        return schoolTimeOrder;
    }

    public void setSchoolTimeOrder(int schoolTimeOrder) {
        this.schoolTimeOrder = schoolTimeOrder;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public SchoolTime() {
    }
}

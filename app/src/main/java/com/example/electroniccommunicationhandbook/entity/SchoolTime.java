package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;

@Entity (tableName = "SchoolTime")
public class SchoolTime {

    private int schoolTimeOrder;
    private Time startingTime;
    private Time endTime;

    public int getSchoolTimeOrder() {
        return schoolTimeOrder;
    }

    public void setSchoolTimeOrder(int schoolTimeOrder) {
        this.schoolTimeOrder = schoolTimeOrder;
    }

    public Time getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Time startingTime) {
        this.startingTime = startingTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public SchoolTime() {
    }
}

package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;

@Entity (tableName = "SchoolTime")
public class SchoolTime {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo (name = "SchoolTimeOrder")
    private int SchoolTimeOrder;

    @ColumnInfo (name = "StartingTime")
    private Time StartingTime;

    @ColumnInfo (name = "EndTime")
    private Time EndTime;

    public int getSchoolTimeOrder() {
        return SchoolTimeOrder;
    }

    public void setSchoolTimeOrder(int schoolTimeOrder) {
        SchoolTimeOrder = schoolTimeOrder;
    }

    public Time getStartingTime() {
        return StartingTime;
    }

    public void setStartingTime(Time startingTime) {
        StartingTime = startingTime;
    }

    public Time getEndTime() {
        return EndTime;
    }

    public void setEndTime(Time endTime) {
        EndTime = endTime;
    }

    public SchoolTime() {
    }
}

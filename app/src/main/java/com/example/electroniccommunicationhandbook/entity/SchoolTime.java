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
    @ColumnInfo (name = "schoolTimeOrder")
    private int schoolTimeOrder;

    @ColumnInfo (name = "schoolTimeStartTime")
    private Time schoolTimeStartTime;

    @ColumnInfo (name = "schoolTimeEndTime")
    private Time schoolTimeEndTime;

    public int getSchoolTimeOrder() {
        return schoolTimeOrder;
    }

    public void setSchoolTimeOrder(int schoolTimeOrder) {
        this.schoolTimeOrder = schoolTimeOrder;
    }

    public Time getSchoolTimeStartTime() {
        return schoolTimeStartTime;
    }

    public void setSchoolTimeStartTime(Time schoolTimeStartTime) {
        this.schoolTimeStartTime = schoolTimeStartTime;
    }

    public Time getSchoolTimeEndTime() {
        return schoolTimeEndTime;
    }

    public void setSchoolTimeEndTime(Time schoolTimeEndTime) {
        this.schoolTimeEndTime = schoolTimeEndTime;
    }

    public SchoolTime() {
    }
}

package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class FeeperCredit {

    private int year;
    private double fee;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public FeeperCredit(int year, double fee) {
        this.year = year;
        this.fee = fee;
    }

    public FeeperCredit() {
    }
}

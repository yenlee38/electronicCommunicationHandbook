package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "FeeperCredit")
public class FeeperCredit {

    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "Year")
    private int Year;

    @ColumnInfo (name = "Fee")
    private double Fee;

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        this.Year = year;
    }

    public double getFee() {
        return Fee;
    }

    public void setFee(double fee) {
        this.Fee = fee;
    }

    public FeeperCredit(int year, double fee) {
        this.Year = year;
        this.Fee = fee;
    }

    public FeeperCredit() {
    }
}

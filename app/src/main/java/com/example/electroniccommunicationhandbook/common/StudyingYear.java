package com.example.electroniccommunicationhandbook.common;

public class StudyingYear {
    int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public StudyingYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return year + " - " + (year + 1);
    }
}

package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Subject")
public class Subject {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "SubjectID")
    private String SubjectID;

    @ColumnInfo(name = "Name")
    private String Name;

    @ColumnInfo(name = "NumberOfCredit")
    private int NumberOfCredit;

    @ColumnInfo(name = "Description")
    private String Description;

    public String getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(String subjectID) {
        SubjectID = subjectID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNumberOfCredit() {
        return NumberOfCredit;
    }

    public void setNumberOfCredit(int numberOfCredit) {
        NumberOfCredit = numberOfCredit;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Subject(@NonNull String subjectID, String name, int numberOfCredit, String description) {
        SubjectID = subjectID;
        Name = name;
        NumberOfCredit = numberOfCredit;
        Description = description;
    }

    public Subject() {
    }
}

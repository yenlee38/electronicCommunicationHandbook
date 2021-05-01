package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Subject")
public class Subject {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "subjectId")
    private int subjectId;

    @ColumnInfo(name = "subjectName")
    private String subjectName;

    @ColumnInfo(name = "subjectNoCredit")
    private int subjectNoCredit;

    @ColumnInfo(name = "subjectDescription")
    private String subjectDescription;

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectNoCredit() {
        return subjectNoCredit;
    }

    public void setSubjectNoCredit(int subjectNoCredit) {
        this.subjectNoCredit = subjectNoCredit;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public Subject(String subjectName, int subjectNoCredit, String subjectDescription) {
        this.subjectName = subjectName;
        this.subjectNoCredit = subjectNoCredit;
        this.subjectDescription = subjectDescription;
    }

    public Subject() {
    }
}

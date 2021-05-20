package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Collection;

public class Subject {

    private String subjectId;
    private String name;
    private int numberOfCredit;
    private String description;
    private Collection<Class> classes;

    public Collection<Class> getClasses() {
        return classes;
    }

    public void setClasses(Collection<Class> classes) {
        this.classes = classes;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfCredit() {
        return numberOfCredit;
    }

    public void setNumberOfCredit(int numberOfCredit) {
        this.numberOfCredit = numberOfCredit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Subject(String subjectId, String name, int numberOfCredit, String description) {
        this.subjectId = subjectId;
        this.name = name;
        this.numberOfCredit = numberOfCredit;
        this.description = description;
    }

    public Subject() {
    }
}

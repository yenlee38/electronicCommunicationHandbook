package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "Parent")
public class Parent {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo (name = "parentId")
    private int parentId;

    @ColumnInfo (name = "parentName")
    private String parentName;

    @ColumnInfo (name = "parentBirthday")
    private Date parentBirthday;

    @ColumnInfo (name = "parentPhone")
    private String parentPhone;

    @ColumnInfo (name = "parentAddress")
    private String parentAddress;

    @ColumnInfo (name = "parentEmail")
    private String parentEmail;

    @ColumnInfo (name = "parentImage")
    private String parentImage;

    @ColumnInfo (name = "accountId") // foreign key with table Account
    private int accountId;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Date getParentBirthday() {
        return parentBirthday;
    }

    public void setParentBirthday(Date parentBirthday) {
        this.parentBirthday = parentBirthday;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getParentAddress() {
        return parentAddress;
    }

    public void setParentAddress(String parentAddress) {
        this.parentAddress = parentAddress;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public String getParentImage() {
        return parentImage;
    }

    public void setParentImage(String parentImage) {
        this.parentImage = parentImage;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Parent() {
    }

    public Parent(int accountId) {
        this.accountId = accountId;
    }
}

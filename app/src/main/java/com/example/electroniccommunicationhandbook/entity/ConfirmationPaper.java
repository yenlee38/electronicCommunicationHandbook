package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class ConfirmationPaper {

    private int confirmationPaperID;
    private String confirmationPaperName;

    public int getConfirmationPaperID() {
        return confirmationPaperID;
    }

    public void setConfirmationPaperID(int confirmationPaperId) {
        this.confirmationPaperID = confirmationPaperId;
    }

    public String getConfirmationPaperName() {
        return confirmationPaperName;
    }

    public void setConfirmationPaperName(String confirmationPaperName) {
        this.confirmationPaperName = confirmationPaperName;
    }

    public ConfirmationPaper() {
    }
}

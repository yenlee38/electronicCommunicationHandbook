package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "ConfirmationPaper")
public class ConfirmationPaper {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo (name = "ConfirmationPaperID")
    private int ConfirmationPaperID;

    @ColumnInfo (name = "ConfirmationPaperName")
    private String ConfirmationPaperName;

    public int getConfirmationPaperID() {
        return ConfirmationPaperID;
    }

    public void setConfirmationPaperID(int confirmationPaperId) {
        this.ConfirmationPaperID = confirmationPaperId;
    }

    public String getConfirmationPaperName() {
        return ConfirmationPaperName;
    }

    public void setConfirmationPaperName(String confirmationPaperName) {
        this.ConfirmationPaperName = confirmationPaperName;
    }

    public ConfirmationPaper() {
    }
}

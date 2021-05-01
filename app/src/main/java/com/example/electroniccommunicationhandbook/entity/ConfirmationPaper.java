package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "ConfirmationPaper")
public class ConfirmationPaper {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo (name = "confirmationPaperId")
    private int confirmationPaperId;

    @ColumnInfo (name = "confirmationPaperName")
    private String confirmationPaperName;

    public int getConfirmationPaperId() {
        return confirmationPaperId;
    }

    public void setConfirmationPaperId(int confirmationPaperId) {
        this.confirmationPaperId = confirmationPaperId;
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

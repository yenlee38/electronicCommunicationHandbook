package com.example.electroniccommunicationhandbook.entity;

import androidx.room.Entity;


public class ConfirmationPaper {

    private int confirmationPaperId;

    private String confirmationPaperName;

    public ConfirmationPaper(int confirmationPaperId, String confirmationPaperName) {
        this.confirmationPaperId = confirmationPaperId;
        this.confirmationPaperName = confirmationPaperName;
    }

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
    public ConfirmationPaper(int confirmationPaperId){
        this.confirmationPaperId = confirmationPaperId;
    }
    public ConfirmationPaper() {
    }
}

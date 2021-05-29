package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

public class Message {

    private Date sentTime;
    private Account senderAccount;
    private Account receiverAccount;
    private String messageContent;

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getContent() {
        return messageContent;
    }

    public void setContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Message() {
    }
}

package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "Message")
public class Message {

    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "sentTime")
    private Date sentTime;

    @ColumnInfo (name = "senderAccountId") // foreign key with table Account
    private int senderAccountId;

    @ColumnInfo (name = "receiverAccountId") // foreign key with table Account
    private int receiverAccountId;

    @ColumnInfo (name = "messageContent")
    private String messageContent;

    @NonNull
    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(@NonNull Date sentTime) {
        this.sentTime = sentTime;
    }

    public int getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(int senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public int getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(int receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Message() {
    }
}

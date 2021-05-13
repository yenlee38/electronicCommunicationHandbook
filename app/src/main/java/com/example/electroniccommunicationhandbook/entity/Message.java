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
    @ColumnInfo (name = "SentTime")
    private Date SentTime;

    @ColumnInfo (name = "SenderAccountID") // foreign key with table Account
    private int SenderAccountID;

    @ColumnInfo (name = "ReceiverAccountID") // foreign key with table Account
    private int ReceiverAccountID;

    @ColumnInfo (name = "Content")
    private String Content;

    @NonNull
    public Date getSentTime() {
        return SentTime;
    }

    public void setSentTime(@NonNull Date sentTime) {
        SentTime = sentTime;
    }

    public int getSenderAccountID() {
        return SenderAccountID;
    }

    public void setSenderAccountID(int senderAccountID) {
        SenderAccountID = senderAccountID;
    }

    public int getReceiverAccountID() {
        return ReceiverAccountID;
    }

    public void setReceiverAccountID(int receiverAccountID) {
        ReceiverAccountID = receiverAccountID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Message() {
    }
}

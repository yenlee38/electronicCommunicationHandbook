package com.example.electroniccommunicationhandbook.entity;

import java.io.Serializable;
import java.util.Date;

public class MessageId {

    private int senderAccountId;

    private int receiverAccountId;

    private Date sentTime;

    public MessageId(int senderAccountId, int receiverAccountId, Date sentTime) {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.sentTime = sentTime;
    }

    public MessageId(int senderAccountId, int receiverAccountId) {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
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

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }
}

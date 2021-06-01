package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

public class Message {

    private Date sentTime;
    private Account senderAccount;
    private Account receiverAccount;
    private String messageContent;
    private MessageId messageId;

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

    public Message(Account senderAccount, Account receiverAccount, String messageContent, Date sentTime) {
        this.sentTime = sentTime;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.messageContent = messageContent;
    }

    public Message (String messageContent, Account receiverAccount,  Account senderAccount){
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.messageContent = messageContent;
        messageId= new MessageId(senderAccount.getAccountID(), receiverAccount.getAccountID(), Calendar.getInstance().getTime());
    }
}

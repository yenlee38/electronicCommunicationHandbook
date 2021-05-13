package com.example.electroniccommunicationhandbook.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Account")
public class Account {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo (name = "AccountID")
    private int AccountID;

    @ColumnInfo (name = "username")
    private String username;

    @ColumnInfo (name = "password")
    private String password;

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int accountID) {
        this.AccountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(){

    }
}

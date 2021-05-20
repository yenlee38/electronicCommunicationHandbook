package com.example.electroniccommunicationhandbook.entity;

public class Account {

    private int accountId;
    private String username;
    private String password;

    public int getAccountID() {
        return accountId;
    }

    public void setAccountID(int accountID) {
        this.accountId = accountID;
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

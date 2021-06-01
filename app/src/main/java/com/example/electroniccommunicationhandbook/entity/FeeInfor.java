package com.example.electroniccommunicationhandbook.entity;

public class FeeInfor {

    private String subjectName;
    private int numberCredit;
    private double totalFee;

    public FeeInfor(String subjectName, int numberCredit, double totalFee) {
        this.subjectName = subjectName;
        this.numberCredit = numberCredit;
        this.totalFee = totalFee;
    }

    public FeeInfor(){}

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNumberCredit() {
        return numberCredit;
    }

    public void setNumberCredit(int numberCredit) {
        this.numberCredit = numberCredit;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }
}

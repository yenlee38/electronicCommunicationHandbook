package com.example.electroniccommunicationhandbook.entity;

public class Fee {
    private  int semester;
    private int studyingYear;
    private double fee; //Fee for one credict

    public Fee(int semester, int studyingYear, double fee) {
        this.semester = semester;
        this.studyingYear = studyingYear;
        this.fee = fee;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getStudyingYear() {
        return studyingYear;
    }

    public void setStudyingYear(int studyingYear) {
        this.studyingYear = studyingYear;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}

package com.example.electroniccommunicationhandbook.entity;

import java.util.Date;

public class OffRequest {

    private int offRequestId;

    private Date createTime;

    private String reason;

    private Teacher sender;

    private Class _class;

    public int getOffRequestId() {
        return offRequestId;
    }

    public void setOffRequestId(int offRequestId) {
        this.offRequestId = offRequestId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime( Date createTime) {
        this.createTime = createTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Teacher getSender() {
        return sender;
    }

    public void setSender(Teacher sender) {
        this.sender = sender;
    }

    public Class get_class() {
        return _class;
    }

    public void set_class(Class _class) {
        this._class = _class;
    }

    public OffRequest() {
    }

    public OffRequest( Date createTime, String reason, Teacher sender, Class _class) {
        this.offRequestId = offRequestId;
        this.createTime = createTime;
        this.reason = reason;
        this.sender = sender;
        this._class = _class;
    }
}


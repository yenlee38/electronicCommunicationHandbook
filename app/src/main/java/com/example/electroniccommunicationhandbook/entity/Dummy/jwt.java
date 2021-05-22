package com.example.electroniccommunicationhandbook.entity.Dummy;

import com.google.gson.annotations.SerializedName;

public class jwt {
    @SerializedName("jwt")
    String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
    public jwt(){}

    public jwt(String token){
        this.jwt=token;
    }
}

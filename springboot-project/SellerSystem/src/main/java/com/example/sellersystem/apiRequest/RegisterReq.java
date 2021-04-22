package com.example.sellersystem.apiRequest;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 *  用于注册时
 *  接收前端的json
 *  */
public class RegisterReq {
    @JsonAlias("userID")
    private String userID;

    @JsonAlias("passWord")
    private String passWord;

    @JsonAlias("country")
    private String country;

    public String getUserID() {
        return userID;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "RegisterReq{" +
                "userID='" + userID + '\'' +
                ", passWord='" + passWord + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

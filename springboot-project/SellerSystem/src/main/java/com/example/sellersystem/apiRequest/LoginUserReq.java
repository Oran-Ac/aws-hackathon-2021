package com.example.sellersystem.apiRequest;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 *  用于登陆时
 *  接收前端的json
 *  */
public class LoginUserReq {

    @JsonAlias("userID")
    String userID;

    @JsonAlias("passWord")
    String password;

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginUserReq{" +
                "userID='" + userID + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

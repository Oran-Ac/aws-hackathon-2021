package com.example.sellersystem.apiRequest;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class DeleteMulGoodsReq {

    @JsonAlias("userID")
    private String userID;

    @JsonAlias("goodsIDList")
    private List<String> goodsIDList;

    public String getUserID() {
        return userID;
    }

    public List<String> getGoodsIDList() {
        return goodsIDList;
    }

    @Override
    public String toString() {
        return "DeleteMulGoodsReq{" +
                "userID='" + userID + '\'' +
                ", goodsIDList=" + goodsIDList +
                '}';
    }
}

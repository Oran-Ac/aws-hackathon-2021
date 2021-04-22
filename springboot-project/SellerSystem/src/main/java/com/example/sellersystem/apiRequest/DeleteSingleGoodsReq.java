package com.example.sellersystem.apiRequest;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 *  用于删除商品时
 *  接收前端传入的 json 对象
 */

public class DeleteSingleGoodsReq {
    @JsonAlias("userID")
    private String userID;

    @JsonAlias("goodsID")
    private String goodsID;

    public String getUserID() {
        return userID;
    }

    public String getGoodsID() {
        return goodsID;
    }

    @Override
    public String toString() {
        return "DeleteGoodsReq{" +
                "userID='" + userID + '\'' +
                ", goodsID=" + goodsID +
                '}';
    }
}

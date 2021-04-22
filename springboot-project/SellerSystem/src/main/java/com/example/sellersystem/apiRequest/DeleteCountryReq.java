package com.example.sellersystem.apiRequest;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

/**
 * 基于国家删除多个商品
 * 用于接收前端的json类
 */

public class DeleteCountryReq {

    @JsonAlias("userID")
    private String userID;

    @JsonAlias("country")
    private String country;

    @JsonAlias("goodsIDList")
    private List<String> goodsIDList;

    public String getUserID() {
        return userID;
    }

    public String getCountry() {
        return country;
    }

    public List<String> getGoodsIDList() {
        return goodsIDList;
    }

    @Override
    public String toString() {
        return "DeleteCountryReq{" +
                "userID='" + userID + '\'' +
                ", country='" + country + '\'' +
                ", countryList=" + goodsIDList +
                '}';
    }
}

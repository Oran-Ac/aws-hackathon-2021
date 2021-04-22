package com.example.sellersystem.apiRequest;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Set;

/**
 * 基于商品删除国家
 * 前端传入的json类
 */
public class DeleteGoodsCountryReq {

    @JsonAlias("userID")
    private String userID;

    @JsonAlias("goodsID")
    private String goodsID;

    @JsonAlias("countryList")
    private Set<String> countryList;

    public String getUserID() {
        return userID;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public Set<String> getCountryList() {
        return countryList;
    }

    @Override
    public String toString() {
        return "DeleteGoodsCountry{" +
                "userID='" + userID + '\'' +
                ", goodsID='" + goodsID + '\'' +
                ", countryList=" + countryList +
                '}';
    }
}

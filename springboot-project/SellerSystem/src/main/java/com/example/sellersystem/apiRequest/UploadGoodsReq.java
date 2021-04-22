package com.example.sellersystem.apiRequest;

/**
 *   用户上传商品时
 *   前端传入的json对象
 */

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Set;

public class UploadGoodsReq {

    @JsonAlias("userID")
    private String userID;

    @JsonAlias("GoodsID")
    private String goodsID;

    @JsonAlias("price")
    private String price;

    @JsonAlias("load")
    private long inStock;

    @JsonAlias("countryList")
    private Set<String> countryList;

    public String getUserID() {
        return userID;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public String getPrice() {
        return price;
    }

    public long getInStock() {
        return inStock;
    }

    public Set<String> getCountryList() {
        return countryList;
    }

    @Override
    public String toString() {
        return "UploadGoodsReq{" +
                "userID=" + userID +
                ", goodsID=" + goodsID +
                ", price='" + price + '\'' +
                ", inStock=" + inStock +
                ", countryList=" + countryList +
                '}';
    }
}

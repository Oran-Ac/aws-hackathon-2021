package com.example.sellersystem.document;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
public class SellerUser {

    @Id
    String sellerID;  //邮箱
    String password;
    String country;

    Set<String> orderID;
    Set<String> goodsID;

    public SellerUser(String sellerID, String password, String country) {
        this.sellerID = sellerID;
        this.password = password;
        this.country = country;
        this.orderID = new HashSet<>();
        this.goodsID = new HashSet<>();
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setOrderID(Set<String> orderID) {
        this.orderID = orderID;
    }

    public void setGoodsID(Set<String> goodsID) {
        this.goodsID = goodsID;
    }

    public Set<String> getOrderID() {
        return orderID;
    }

    public Set<String> getGoodsID() {
        return goodsID;
    }
}

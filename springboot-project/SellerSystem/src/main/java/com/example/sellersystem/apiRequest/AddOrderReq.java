package com.example.sellersystem.apiRequest;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;


/**
 * 添加订单的前端传入json
 */
public class AddOrderReq {

    String userID;
    String customerID;
    String customerCountry;
    String goodsID;
    int count;   //件数
    String date;  //到日

    @JsonAlias("price")
    String amount;

    @JsonAlias("tax")
    String taxes;
    boolean done;   //是否已经完成？


    public AddOrderReq(String userID, String customerID, String customerCountry, String goodsID, int count,
                       String date, String amount, String taxes, boolean done) {
        this.userID = userID;
        this.customerID = customerID;
        this.customerCountry = customerCountry;
        this.goodsID = goodsID;
        this.count = count;
        this.date = date;
        this.amount = amount;
        this.taxes = taxes;
        this.done = done;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public String getUserID() {
        return userID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public int getCount() {
        return count;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public String getTaxes() {
        return taxes;
    }

    public boolean isDone() {
        return done;
    }
}

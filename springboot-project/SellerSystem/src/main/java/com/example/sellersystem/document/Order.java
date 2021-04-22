package com.example.sellersystem.document;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Order {

    @Id
    String orderID;

    String customerID;
    String customerCountry;
    String goodsID;
    int count;   //件数
    Date date;  //到日

    String amount;
    String taxes;
    boolean done;   //是否已经完成？

    public Order(String orderID, String customerID, String customerCountry, String goodsID,
                 int count, Date date, String amount, String taxes, boolean done) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.customerCountry = customerCountry;
        this.goodsID = goodsID;
        this.count = count;
        this.date = date;
        this.amount = amount;
        this.taxes = taxes;
        this.done = done;
    }




    //getters and setters
    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", customerCountry='" + customerCountry + '\'' +
                ", goodsID='" + goodsID + '\'' +
                ", count=" + count +
                ", date=" + date +
                ", amount='" + amount + '\'' +
                ", taxes='" + taxes + '\'' +
                ", done=" + done +
                '}';
    }
}

package com.example.sellersystem.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Document
public class Goods {

    @Id
    String goodsID;

    /* 商家添加商品时修改 */
    String goodsName;
    String about;
    int numberOfImages;   //商品描述图的数量
    String country;  //发货地

    boolean uploaded;  //是否发布

    /* 商家发布商品时修改 */
    String price;
    long inStock;  //库存
    Set<String> sellCountries;

    /* 商家不可修改 */
    Map<String, List<String>> orderIDListByCountry;   //收货国家的对应订单 ID 集合

    public Goods(String goodsID, String goodsName, String about, int numberOfImages, String country) {
        this.goodsID = goodsID;
        this.goodsName = goodsName;
        this.about = about;
        this.numberOfImages = numberOfImages;
        this.country = country;
        this.uploaded = false;
        this.price = null;
        this.inStock = -1;
        this.sellCountries=null;
        this.orderIDListByCountry = new HashMap<>();
    }

    //getters and setters
    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getNumberOfImages() {
        return numberOfImages;
    }

    public void setNumberOfImages(int numberOfImages) {
        this.numberOfImages = numberOfImages;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getInStock() {
        return inStock;
    }

    public void setInStock(long inStock) {
        this.inStock = inStock;
    }

    public Set<String> getSellCountries() {
        return sellCountries;
    }

    public void setSellCountries(Set<String> sellCountries) {
        this.sellCountries = sellCountries;
    }

    public Map<String, List<String>> getOrderIDListByCountry() {
        return orderIDListByCountry;
    }

    public void setOrderIDListByCountry(Map<String, List<String>> orderIDListByCountry) {
        this.orderIDListByCountry = orderIDListByCountry;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsID='" + goodsID + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", about='" + about + '\'' +
                ", numberOfImages=" + numberOfImages +
                ", country='" + country + '\'' +
                ", uploaded=" + uploaded +
                ", price='" + price + '\'' +
                ", inStock=" + inStock +
                ", sellCountries=" + sellCountries +
                ", orderIDListByCountry=" + orderIDListByCountry +
                '}';
    }
}

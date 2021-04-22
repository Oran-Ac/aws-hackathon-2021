package com.example.sellersystem.apiResponse;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class AddGoodsResp {

    private final int code;
    private final String info;
    private final String goodsID;

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public AddGoodsResp(int code, String info, String goodsID) {
        this.code = code;
        this.info = info;
        this.goodsID = goodsID;
    }

    @Override
    public String toString() {
        return "AddGoodsResp{" +
                "code=" + code +
                ", info='" + info + '\'' +
                ", goodsID='" + goodsID + '\'' +
                '}';
    }
}

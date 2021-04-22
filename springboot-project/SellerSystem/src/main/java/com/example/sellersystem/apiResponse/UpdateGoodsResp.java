package com.example.sellersystem.apiResponse;


/*
 *  用户更新商品时
 *  返回的枚举类
 * */

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UpdateGoodsResp {
    UPDATE_SUCCESS(0, "商品更新成功"),
    USER_NOT_FOUND(1, "用户不存在"),
    USER_DONT_HAVE_GOODS(2, "用户没有这个商品"),
    GOODSID_NOT_FOUND(3,"找不到商品ID"),
    GOODS_IS_UPLOADED(4, "商品已经上架，无法更新");


    private final int code;
    private final String info;

    @Override
    public String toString() {
        return "UpdateGoodsResp{" +
                "code=" + code +
                ", info='" + info + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    UpdateGoodsResp(int code, String info) {
        this.code = code;
        this.info = info;
    }
}

package com.example.sellersystem.apiResponse;

/*
*  用于上传商品时
*  返回给前端的枚举类型
*  */

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UploadGoodsResp {

    UPLOAD_SUCCESS(0, "上架成功！"),
    USER_NOT_FOUND(1, "用户不存在"),
    GOODSID_NOT_FOUND(2, "找不到用户的商品");

    private final int code;
    private final String info;

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    UploadGoodsResp(int code, String info) {
        this.code = code;
        this.info = info;
    }

    @Override
    public String toString() {
        return "UploadGoodsResp{" +
                "code=" + code +
                ", info='" + info + '\'' +
                '}';
    }
}

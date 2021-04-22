package com.example.sellersystem.apiResponse;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 删除一个或者多个商品的返回类
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DeleteGoodsResp {

    DELETE_SUCCESS(0, "成功删除/下架商品"),
    USER_NOT_FOUND(1, "用户不存在"),
    GOODS_NOT_FOUND(2, "(部分)商品不存在");

    private final int code;
    private final String info;

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    DeleteGoodsResp(int code, String info) {
        this.code = code;
        this.info = info;
    }

    @Override
    public String toString() {
        return "DeleteGoodsResp{" +
                "code=" + code +
                ", info='" + info + '\'' +
                '}';
    }
}

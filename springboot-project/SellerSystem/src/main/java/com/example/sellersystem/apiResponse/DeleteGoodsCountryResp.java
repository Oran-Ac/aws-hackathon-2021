package com.example.sellersystem.apiResponse;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 基于商品删除国家
 * 后端返回类
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DeleteGoodsCountryResp {

    DELETE_SUCCESS(0, "删除成功！"),
    USER_NOTFOUND(1, "用户不存在"),
    NOT_HAVE_PERMISSION(2, "用户没有权限操作该商品");

    private final int code;
    private final String info;

    DeleteGoodsCountryResp(int code, String info) {
        this.code = code;
        this.info = info;
    }

    @Override
    public String toString() {
        return "DeleteGoodsCountryResp{" +
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
}

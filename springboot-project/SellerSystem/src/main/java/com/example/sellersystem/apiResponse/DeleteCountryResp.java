package com.example.sellersystem.apiResponse;


import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DeleteCountryResp {

    DELETE_SUCCESS(0, "修改成功！"),
    USER_NOT_FOUND(1, "用户不存在"),
    PARTABLE_DELELT(2, "部分修改成功！存在部分商品本就没有该国家"),
    NO_PERMISSION(3, "没有权限，存在部分商品不属于该买家");

    private final int code;
    private final String info;

    DeleteCountryResp(int code, String info) {
        this.code = code;
        this.info = info;
    }

    @Override
    public String toString() {
        return "DeleteCountryRep{" +
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

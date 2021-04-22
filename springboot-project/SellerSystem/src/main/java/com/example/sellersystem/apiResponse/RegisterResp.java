package com.example.sellersystem.apiResponse;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;


/*
 *  用于注册时
 *  返回给前端的枚举类型
 *  */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RegisterResp {

    REGISTER_SUCCESS(0, "注册成功！"),
    USER_FOUND(1, "用户名已存在");

    private final int code;
    private final String info;

    RegisterResp(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "RegisterRes{" +
                "code=" + code +
                ", info='" + info + '\'' +
                '}';
    }
}

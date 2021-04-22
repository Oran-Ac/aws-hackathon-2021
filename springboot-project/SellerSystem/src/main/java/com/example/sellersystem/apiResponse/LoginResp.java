package com.example.sellersystem.apiResponse;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用于登陆时
 *  返回给前端的枚举类型
 * */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LoginResp {

    SUCCESS(0, "成功登入"),
    USER_NOT_FOUND(1, "未找到用户"),
    INCORRECT_PASSWORD(2, "密码错误");

    private final int code;
    private final String info;

    LoginResp(int code, String info) {
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
        return "LoginResult{" +
                "code=" + code +
                ", info='" + info + '\'' +
                '}';
    }
}

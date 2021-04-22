package com.example.sellersystem.apiResponse;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 添加订单的返回
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class AddOrderResp {

    private final int code;
    private final String info;
    private final String newOrderID;

    public AddOrderResp(int code, String info, String newOrderID){
        this.code = code;
        this.info = info;
        this.newOrderID = newOrderID;
    }

    public String getNewOrderID() {
        return newOrderID;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "AddOrderResp{" +
                "code=" + code +
                ", info='" + info + '\'' +
                ", newOrderID='" + newOrderID + '\'' +
                '}';
    }
}

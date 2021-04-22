package com.example.sellersystem.controller;

import com.example.sellersystem.apiRequest.AddOrderReq;
import com.example.sellersystem.apiResponse.AddOrderResp;
import com.example.sellersystem.document.Order;
import com.example.sellersystem.server.OrderServer;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    private final OrderServer orderServer;

    @Autowired
    public OrderController(OrderServer orderServer) {
        this.orderServer = orderServer;
    }

    @GetMapping("order")
    public Iterable<Order> getOrderList(@RequestParam("userID") String uid) {
        log.info("显示卖家所有订单 GET Request:/order -------- [前端传入：userID {}]", uid);
        Iterable<Order> res = orderServer.getUserOrder(uid);
        log.info("显示卖家所有订单 GET Response:/order -------- [后端返回：{}]", res);
        return res;
    }

    @PostMapping("order/add")
    public AddOrderResp addOrder(@RequestBody AddOrderReq req) {
        log.info("卖家添加订单 POST Request:/order/add -------- [前端传入：{}]", req);
        AddOrderResp res = orderServer.addOrder(
                req.getUserID(),
                req.getCustomerID(),
                req.getCustomerCountry(),
                req.getGoodsID(),
                req.getCount(),
                req.getDate(),
                req.getAmount(),
                req.getTaxes(),
                req.isDone()
        );
        log.info("卖家添加订单 POST Response:/order/add -------- [后端返回：{}]", res);
        return res;
    }

    @GetMapping("/top3country/search")
    public List<Pair<String, Long>> getTop3Countries(@RequestParam("goodsID") String goodsID,
                                                     @RequestParam("beginTime") String begin,
                                                     @RequestParam("endTime") String end) {

        log.info("查找商品前三销售国 POST Request:/top3country/search -------- [前端传入：" +
                "goodsID:{}, begin:{}, end:{}]", goodsID, begin, end);
        List<Pair<String, Long>> res = orderServer.getTop3Countries(goodsID, begin, end);
        log.info("查找商品前三销售国 POST Response:/top3country/search -------- [后端返回：{}]", res);
        return res;
    }
}

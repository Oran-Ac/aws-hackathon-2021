package com.example.sellersystem.controller;

import com.example.sellersystem.document.Goods;
import com.example.sellersystem.server.GoodsServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/* 实现商品基本的请求
 *  1. 获取用户所有商品
 *  2. 根据商品ID搜索商品
 * */

@RestController
@Slf4j
public class GoodsController {
    private final GoodsServer goodsServer;
    @Autowired
    public GoodsController(GoodsServer goodsServer) {
        this.goodsServer = goodsServer;
    }

    @GetMapping("/goods")
    public List<Goods> getAllGoods(@RequestParam("userID") String uid) {
        log.info("列举商品 GET Request:/goods -------- [前端传入：userID:{}]", uid);
        List<Goods> res = goodsServer.getAllGoods(uid);
        log.info("列举商品 GET Response:/goods -------- [后端返回：{}]", res);
        return res;
    }

//    @GetMapping("/goods/search")
//    public Goods searchGoods(@RequestParam("gooodsID")long gid) {
//        return goodsServer.getGoods(gid);
//    }
}

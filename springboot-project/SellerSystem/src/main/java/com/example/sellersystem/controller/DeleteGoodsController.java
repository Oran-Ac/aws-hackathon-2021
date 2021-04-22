package com.example.sellersystem.controller;


import com.example.sellersystem.apiRequest.DeleteCountryReq;
import com.example.sellersystem.apiRequest.DeleteGoodsCountryReq;
import com.example.sellersystem.apiRequest.DeleteMulGoodsReq;
import com.example.sellersystem.apiRequest.DeleteSingleGoodsReq;
import com.example.sellersystem.apiResponse.DeleteCountryResp;
import com.example.sellersystem.apiResponse.DeleteGoodsCountryResp;
import com.example.sellersystem.apiResponse.DeleteGoodsResp;
import com.example.sellersystem.server.GoodsServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 完成前端的三个删除请求：
 * 1. 删除一个商品  （已经上架的就下架，没有上架的就彻底删除）
 * 2. 删除多个商品  （已经上架的就下架，没有上架的就彻底删除）
 * 3. 让某个商品不在某些国家售卖
 * 4. 让多个商品不在某个国家售卖
 */
@RestController
@Slf4j
public class DeleteGoodsController {

    private final GoodsServer goodsServer;

    @Autowired
    public DeleteGoodsController(GoodsServer goodsServer) {
        this.goodsServer = goodsServer;
    }

    //1. 删除一个商品  （已经上架的就下架，没有上架的就彻底删除）
    @DeleteMapping ("/goods/delete/single")
    public DeleteGoodsResp deleteSingleGoods(@RequestBody DeleteSingleGoodsReq req) {
        log.info("删除单个商品 DELETE Request:/goods/delete/single -------- [前端传入：{}]", req);
        DeleteGoodsResp res =  goodsServer.delete(req.getUserID(), req.getGoodsID());
        log.info("删除单个商品 DELETE Response:/goods/delete/single -------- [后端返回：{}]", res);
        return res;
    }

    //2. 删除多个商品  （已经上架的就下架，没有上架的就彻底删除）
    @DeleteMapping("/goods/delete/multiple")
    public DeleteGoodsResp deleteMultiple(@RequestBody DeleteMulGoodsReq req) {

        log.info("删除多个商品 DELETE Request:/goods/delete/multiple -------- [前端传入：{}]", req);

        DeleteGoodsResp res = DeleteGoodsResp.DELETE_SUCCESS;
        //依次删除每个商品
        for(String goodsID : req.getGoodsIDList()) {
            //删除商品
            DeleteGoodsResp tempRes = goodsServer.delete(req.getUserID(), goodsID);
            if(tempRes != DeleteGoodsResp.DELETE_SUCCESS) {
                res = tempRes;
            }
        }
        log.info("删除多个商品 DELETE Response:/goods/delete/multiple -------- [后端返回：{}]", res);
        return res;  //只有全是正常删除才会返回成功（res的初始值），否则返回最后一次的错误
    }

    //3. 让某个商品不在多个国家售卖
    @PutMapping("/goods/country/delete")
    private DeleteGoodsCountryResp deleteCountry(@RequestBody DeleteGoodsCountryReq req) {
        log.info("基于商品删除国家 PUT Request:/goods/country/delete -------- [前端传入：{}]", req);
        DeleteGoodsCountryResp res =  goodsServer.delete2(req.getUserID(), req.getGoodsID(), req.getCountryList());
        log.info("基于商品删除国家 PUT Request:/goods/country/delete -------- [后端返回：{}]", res);
        return res;
    }

    //4. 让多个商品在某个国家不可售卖
    @PutMapping("/country/delete")
    public DeleteCountryResp deleteCountries(@RequestBody DeleteCountryReq req) {
        log.info("基于国家删除商品 PUT Request:/country/delete -------- [前端传入：{}]", req);
        DeleteCountryResp res = goodsServer.delete3(req.getUserID(), req.getCountry(), req.getGoodsIDList());
        log.info("基于国家删除商品 PUT Request:/country/delete -------- [后端返回：{}]", res);
        return res;
    }


}

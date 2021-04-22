package com.example.sellersystem.controller;

import com.example.sellersystem.apiRequest.UploadGoodsReq;
import com.example.sellersystem.apiResponse.AddGoodsResp;
import com.example.sellersystem.apiResponse.UpdateGoodsResp;
import com.example.sellersystem.apiResponse.UploadGoodsResp;
import com.example.sellersystem.server.GoodsServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/** 实现商品添加的请求
 *  1. 添加商品
 *  2. 更新商品
 *  3. 上架商品
 * */

@Slf4j
@RestController
public class AddGoodsController {

    private final GoodsServer goodsServer;

    @Autowired
    public AddGoodsController(GoodsServer goodsServer) {
        this.goodsServer = goodsServer;
    }

    /**
     * 最终版本
     * 最后图片服务转移到云，由前端直接传输和拿取，
     * 之前写的本地服务器存图的功能不用了，且前端始终无法传到文件数组。。
     * 这里直接允许图片为空
     */
    @PostMapping("/goods/add")
    public AddGoodsResp addGoods(@RequestParam("userID") String userID,
                                  @RequestParam("goodsName") String goodsName,
                                  @RequestParam("about") String about,
                                  @RequestParam("country") String country,
                                  @RequestPart(value = "images", required = false)
                                             List<MultipartFile> images) throws IOException {
        log.info("添加商品 POST Request:/goods/add -------- [前端传入：goodsName:{}, " +
                "about:{}, country:{}, images{}]", goodsName, about, country, images);

        AddGoodsResp result = goodsServer.addGoods(
                userID,
                goodsName,
                about,
                images,
                country
        );

        log.info("添加商品 POST Response:/goods/add -------- [后端返回：{}]", result);
        return result;
    }

    /**
     * 为了测试 下面写了三个add接口
     * 均能通过postman测试
     */
    @PostMapping("/goods/add0")
    public AddGoodsResp addGoods0(@RequestParam("userID") String userID,
                                 @RequestParam("goodsName") String goodsName,
                                 @RequestParam("about") String about,
                                 @RequestParam("country") String country,
                                 @RequestPart("images") List<MultipartFile> images) throws IOException {
        log.info("添加商品 POST Request:/goods/add0 -------- [前端传入：goodsName:{}, " +
                "about:{}, country:{}, images{}]", goodsName, about, country, images);

        AddGoodsResp result = goodsServer.addGoods(
                userID,
                goodsName,
                about,
                images,
                country
        );

        log.info("添加商品 POST Response:/goods/add0 -------- [后端返回：{}]", result);
        return result;
    }

    @PostMapping("/goods/add1")
    public AddGoodsResp addGoods1(@RequestParam("userID") String userID,
                                 @RequestParam("goodsName") String goodsName,
                                 @RequestParam("about") String about,
                                 @RequestParam("country") String country,
                                 @RequestParam("images") MultipartFile[] images) throws IOException {
        log.info("添加商品 POST Request:/goods/add1 -------- [前端传入：goodsName:{}, " +
                "about:{}, country:{}, images{}]", goodsName, about, country, images);

        List<MultipartFile> list = Arrays.asList(images);
        AddGoodsResp result = goodsServer.addGoods(
                userID,
                goodsName,
                about,
                list,
                country
        );

        log.info("添加商品 POST Response:/goods/add1 -------- [后端返回：{}]", result);
        return result;
    }

    @PostMapping("/goods/add2")
    public AddGoodsResp addGoods2(@RequestParam("userID") String userID,
                                  @RequestParam("goodsName") String goodsName,
                                  @RequestParam("about") String about,
                                  @RequestParam("country") String country,
                                  @RequestPart("images") MultipartFile[] images) throws IOException {
        log.info("添加商品 POST Request:/goods/add2 -------- [前端传入：goodsName:{}, " +
                "about:{}, country:{}, images{}]", goodsName, about, country, images);

        List<MultipartFile> list = Arrays.asList(images);
        AddGoodsResp result = goodsServer.addGoods(
                userID,
                goodsName,
                about,
                list,
                country
        );

        log.info("添加商品 POST Response:/goods/add2 -------- [后端返回：{}]", result);
        return result;
    }

    @PutMapping("/goods/update")
    public UpdateGoodsResp updateGoods(@RequestParam("userID") String userID,
                                       @RequestParam("goodsID") String goodsID,
                                       @RequestParam("goodsName") String goodsName,
                                       @RequestParam("about") String about,
                                       @RequestParam("country") String country,
                                       @RequestPart(value = "images",required = false) List<MultipartFile> images) throws IOException {

        log.info("更新商品 PUT Request:/goods/update -------- [前端传入：goodsName:{},  " +
                "about:{}, country:{}, images{}]", goodsName, about, country, images);

        UpdateGoodsResp res = goodsServer.updateGoods(
                userID,
                goodsID,
                goodsName,
                about,
                images,
                country
        );

        log.info("更新商品 PUT Response:/goods/update --------  [后端返回: {}]", res);
        return res;
    }

    @PostMapping("/goods/upload")
    public UploadGoodsResp uploadGoods(@RequestBody UploadGoodsReq uploadGoodsReq) {
        log.info("上架商品 POST Request:/goods/upload -------- [前端传入：{}]", uploadGoodsReq);

        UploadGoodsResp res = goodsServer.uploadGoods(
                uploadGoodsReq.getUserID(),
                uploadGoodsReq.getGoodsID(),
                uploadGoodsReq.getPrice(),
                uploadGoodsReq.getInStock(),
                uploadGoodsReq.getCountryList()
        );

        log.info("上架商品 POST Response:/goods/upload -------- [后端返回：{}]", res);
        return res;
    }
}

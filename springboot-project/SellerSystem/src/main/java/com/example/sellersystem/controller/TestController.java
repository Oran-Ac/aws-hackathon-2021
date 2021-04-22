//package com.example.sellersystem.controller;
//
//import com.example.sellersystem.server.S3Server;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@RestController
//public class TestController {
//
//    S3Server s3Server;
//
//    @Autowired
//    public TestController(S3Server s3Server) {
//        this.s3Server = s3Server;
//    }
//
//    @RequestMapping("/test1")
//    public boolean uploadImage(@RequestParam("goodsID") long goodsID,
//                               @RequestParam("file") List<MultipartFile> images) {
//        return s3Server.uploadImages(goodsID, images);
//    }
//}
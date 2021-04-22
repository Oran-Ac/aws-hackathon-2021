//package com.example.sellersystem.server;
//
//
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
///*
//* 将文件上传到 aws s3
//* */
//@Service
//public class S3Server {
//    private final AmazonS3 s3;
//
//    //在构造组件时就直接开启服务
//    public S3Server() {
//        this.s3 = AmazonS3ClientBuilder.standard().withRegion("us-east-1").build();
//    }
//
//    //将文件流上传到s3
//    private void uploadFile(String filename, InputStream inputStream, long size, String contentType) {
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentType(contentType);
//        metadata.setContentLength(size);
//
//        try {
//            s3.putObject("awspicture",   //存储桶
//                            filename,   //文件名
//                            inputStream,   //文件流
//                            metadata
//                    );
//        } catch (AmazonServiceException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //将某商品的所有图片上传到 s3
//    public boolean uploadImages(Long goodsID, List<MultipartFile> images) {
//        for(int i = 0; i < images.size(); i++) {
//            MultipartFile image = images.get(i);
//            try {
//                this.uploadFile(goodsID + i + ".png",  //设定上传图片名字
//                                image.getInputStream(),
//                                image.getSize(),
//                                image.getContentType()
//                );
//            } catch (IOException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//        return false;
//    }
//}

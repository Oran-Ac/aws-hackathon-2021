package com.example.sellersystem.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


/*
* 将文件上传到服务器本机
* */

@Service
@Slf4j
public class ImageUploadServer {

    private final String baseURL = "./GoodsImages";

    //上传文件到本地
    private void uploadImage(String goodsID, MultipartFile file, int count) throws IOException {
        //文件路径
        //上服务器可以直接设定路径
//        String url = baseURL + "/" + goodsID + "/" + count + ".png";

        //在本机上必须这样子找路径才行...
        String url = new File(baseURL).getAbsolutePath() + "/" + goodsID + "/" + count + ".png";

        //在本地创建文件
        File newFile = new File(url);
        //将文件转移到本地
        file.transferTo(newFile);
    }


    //1. 为商品添加文件夹
    public void addDir(String goodsID) {
        log.info("创建文件夹 /GoodsImages/{}/....", goodsID);
        boolean res;

        File file = new File(baseURL + "/" + goodsID);
        if (!file.exists()) {
            res = file.mkdirs();   //创建文件夹，如果上级文件夹不存在也会一起创建
        } else {
            res = false;
        }

        //打印相关日志
        if (res) {
            log.info("文件夹创建成功！");
        } else {
            log.info("文件夹已存在，创建失败！");
        }
    }

    //2. 上传该商品的所有图片
    public void uploadImages(String goodsID, List<MultipartFile> file) throws IOException {
        if(file == null)
            return;
        for (int i = 0; i < file.size(); i++) {
            uploadImage(goodsID, file.get(i), i);
        }
    }


    //3. 删除商品文件夹下所有图片
    public void deleteImages(String gid, int count) {
        for(int i = 0; i < count; i++) {
            String url = new File(baseURL).getAbsolutePath() + "/" + gid + "/" + i + ".png";
            File file = new File(url);
            if(!file.exists()) {
                log.info("文件{}不存在，无法删除", url);
            } else if(file.delete()){
                log.info("文件{}删除成功！", url);
            }
        }
    }

    //4. 删除商品文件夹
    public void deleteDir(String gid, int count) {
        deleteImages(gid, count);
        String url = new File(baseURL).getAbsolutePath() + "/" + gid;
        File file = new File(url);
        if(!file.exists()) {
            log.info("文件夹{}不存在，无法删除", url);
        } else if(file.delete()){
            log.info("文件{}删除成功！", url);
        }
    }

}

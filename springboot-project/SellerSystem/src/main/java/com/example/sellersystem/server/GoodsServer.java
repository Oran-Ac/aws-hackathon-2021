package com.example.sellersystem.server;

import com.example.sellersystem.apiResponse.*;
import com.example.sellersystem.dao.IGoodsRepo;
import com.example.sellersystem.dao.ISellerUserRepo;
import com.example.sellersystem.document.Goods;
import com.example.sellersystem.document.SellerUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class GoodsServer {

    private final IGoodsRepo goodsRepo;
    private final ISellerUserRepo sellerUserRepo;
    private final ImageUploadServer uploadServer;

    @Autowired
    public GoodsServer(IGoodsRepo goodsRepo,
                       ISellerUserRepo sellerUserRepo,
                       ImageUploadServer imageUploadServer) {
        this.goodsRepo = goodsRepo;
        this.sellerUserRepo = sellerUserRepo;
        this.uploadServer = imageUploadServer;
    }

    //根据用户 id 找到其发布的所有商品对象
    public List<Goods> getAllGoods(String uid) {
        SellerUser sellerUser = sellerUserRepo.getUser(uid);
        if (null == sellerUser)   //当用户没有发布商品时
            return null;
        Set<String> goodsIDs = sellerUser.getGoodsID();
        List<Goods> goods = new ArrayList<>();
        for (String id : goodsIDs) {
            goods.add(goodsRepo.getGoods(id));
        }
        return goods;
    }

    //1. 添加商品
    public AddGoodsResp addGoods(String uid, String name, String about, List<MultipartFile> images,
                                 String country) throws IOException {

        SellerUser sellerUser = sellerUserRepo.getUser(uid);
        if (null == sellerUser) {
            return new AddGoodsResp(1, "用户不存在", null);
        }

        int numberOfImages = 0;
        if(null != images)
            numberOfImages = images.size();

        String goodsID = goodsRepo.insertGoods(
                name,
                about,
                numberOfImages,   //存入数据库的是图片的数量
                country
        );
        //对卖家添加商品
        sellerUser.getGoodsID().add(goodsID);
        sellerUserRepo.save(sellerUser);
        //创建商品的文件夹
        uploadServer.addDir(goodsID);
        // 将商品图上传到本地
        uploadServer.uploadImages(goodsID, images);

        return new AddGoodsResp(0, "商品增添成功", goodsID);
    }

    //2. 更新商品
    public UpdateGoodsResp updateGoods(String uid, String goodsID, String name, String about,
                                       List<MultipartFile> images, String country) throws IOException {

        SellerUser sellerUser = sellerUserRepo.getUser(uid);
        if (null == sellerUser) {
            return UpdateGoodsResp.USER_NOT_FOUND;
        } else if (sellerUser.getGoodsID() == null || !sellerUser.getGoodsID().contains(goodsID)) {
            return UpdateGoodsResp.USER_DONT_HAVE_GOODS;
        }

        //先找到已经上传的商品
        Goods goods = goodsRepo.getGoods(goodsID);

        if (goods == null) {
            return UpdateGoodsResp.GOODSID_NOT_FOUND;
        } else if (goods.isUploaded()) {
            return UpdateGoodsResp.GOODS_IS_UPLOADED;
        } else {
            //删除之前的图片文件
            uploadServer.deleteImages(goodsID, goods.getNumberOfImages());
            //更新图片到本地
            uploadServer.uploadImages(goodsID, images);
            //更新商品数据
            goods.setGoodsName(name);
            goods.setAbout(about);
            goods.setCountry(country);
            if (null != images)  //传入图片允许为空
                goods.setNumberOfImages(images.size());
            else
                goods.setNumberOfImages(0);
            //更新到数据库
            goodsRepo.save(goods);
            return UpdateGoodsResp.UPDATE_SUCCESS;
        }
    }

    //3. 上传商品
    public UploadGoodsResp uploadGoods(String uid, String goodsID, String price, long inStock, Set<String> countryList) {

        SellerUser sellerUser = sellerUserRepo.getUser(uid);
        if (null == sellerUser) {
            return UploadGoodsResp.USER_NOT_FOUND;
        } else if (sellerUser.getGoodsID() == null || !sellerUser.getGoodsID().contains(goodsID)) {
            return UploadGoodsResp.GOODSID_NOT_FOUND;
        } else {
            Goods goods = goodsRepo.getGoods(goodsID);
            goods.setPrice(price);
            goods.setInStock(inStock);
            goods.setSellCountries(countryList);
            goods.setUploaded(true);
            goodsRepo.save(goods);
            return UploadGoodsResp.UPLOAD_SUCCESS;
        }
    }

    //4. 删除商品（已经发布则只是修改状态，未发布的直接删除！）
    public DeleteGoodsResp delete(String uid, String gid) {
        SellerUser sellerUser = sellerUserRepo.getUser(uid);
        if (null == sellerUser) {
            return DeleteGoodsResp.USER_NOT_FOUND;
        } else if (null == sellerUser.getGoodsID() || !sellerUser.getGoodsID().contains(gid)) {
            return DeleteGoodsResp.GOODS_NOT_FOUND;
        }
        Goods goods = goodsRepo.getGoods(gid);

        if(goods.isUploaded()) {   //已经发布则只是修改状态
            goods.setUploaded(false);
            goodsRepo.save(goods);
        } else {   //如果还没有发布，直接删除
            //删除本地图图
            uploadServer.deleteDir(gid, goods.getNumberOfImages());
            //更新用户的商品列表
            sellerUser.getGoodsID().remove(gid);
            sellerUserRepo.save(sellerUser);
            //删除商品数据
            goodsRepo.deleteById(gid);
        }
        return DeleteGoodsResp.DELETE_SUCCESS;
    }

    //5. 基于商品删除多个国家（修改成新的list）
    public DeleteGoodsCountryResp delete2(String uid, String goodsID, Set<String> countryList) {
        SellerUser sellerUser = sellerUserRepo.getUser(uid);
        if (null == sellerUser)
            return DeleteGoodsCountryResp.USER_NOTFOUND;
        if (!sellerUser.getGoodsID().contains(goodsID))
            return DeleteGoodsCountryResp.NOT_HAVE_PERMISSION;

        //修改操作
        Goods goods = goodsRepo.getGoods(goodsID);
        //保存到数据库
        goods.setSellCountries(countryList);
        goodsRepo.save(goods);
        return DeleteGoodsCountryResp.DELETE_SUCCESS;
    }

    //6. 让多个商品不在某个国家售卖
    public DeleteCountryResp delete3(String userID, String country, List<String> goodsIDList) {

        SellerUser sellerUser = sellerUserRepo.getUser(userID);
        if (null == sellerUser)
            return DeleteCountryResp.USER_NOT_FOUND;

        Set<String> userGoodsIdList = sellerUser.getGoodsID();
        if (userGoodsIdList == null)
            return DeleteCountryResp.NO_PERMISSION;

        boolean flag1 = false;
        boolean flag2 = false;
        for (String gid : goodsIDList) {
            if (!sellerUser.getGoodsID().contains(gid)) {
                flag1 = true;
                continue;
            }
            Goods goods = goodsRepo.getGoods(gid);
            Set<String> sellCountries = goods.getSellCountries();
            if (sellCountries == null || !sellCountries.contains(country)) {
                flag2 = true;
                continue;
            }
            //移除国家
            sellCountries.remove(country);
            //保存操作
            goods.setSellCountries(sellCountries);
            goodsRepo.save(goods);
        }

        if (flag1)
            return DeleteCountryResp.NO_PERMISSION;
        if (flag2)
            return DeleteCountryResp.PARTABLE_DELELT;
        return DeleteCountryResp.DELETE_SUCCESS;
    }
}

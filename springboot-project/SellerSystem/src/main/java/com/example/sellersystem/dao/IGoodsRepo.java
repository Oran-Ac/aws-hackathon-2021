package com.example.sellersystem.dao;

import com.example.sellersystem.document.Goods;
import com.example.sellersystem.utils.UUIDUtil;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGoodsRepo extends MongoRepository<Goods, String> {

    default Goods getGoods(String id) {
        return this.findById(id).orElse(null);
    }

    //插入商品，返回商品ID
    default String insertGoods(String name, String about, int numberOfImages, String country) {
        String id = UUIDUtil.getShortUuid();
        Goods goods = new Goods(
                id,   //保证不会重复
                name,
                about,
                numberOfImages,
                country
        );
        this.insert(goods);
        return id;
    }
//
//    default void updateGoods(long id, String name, String about, List<File> images, String country) {
//
//
//    }
//
//    default void defineGoods(long id, String price, long inStock, List<String> sellCountry) {
//
//    }
//
//    default boolean deleteGoods(long id) {
//
//    }
//
//    default void deleteCountry(long id, String country) {
//
//    }
}

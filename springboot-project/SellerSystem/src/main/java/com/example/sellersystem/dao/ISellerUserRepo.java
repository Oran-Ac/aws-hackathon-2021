package com.example.sellersystem.dao;

import com.example.sellersystem.document.SellerUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISellerUserRepo extends MongoRepository<SellerUser, String> {
    //从数据库中取出user
    default SellerUser getUser(String uid) {
        return this.findById(uid).orElse(null);
    }

    //加入用户
    default void insertUser(String userID, String password, String country) {
        SellerUser user = new SellerUser(userID, password, country);
        this.insert(user);
    }
}

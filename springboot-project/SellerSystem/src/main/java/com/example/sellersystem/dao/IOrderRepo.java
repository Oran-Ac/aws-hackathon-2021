package com.example.sellersystem.dao;

import com.example.sellersystem.document.Order;
import com.example.sellersystem.utils.UUIDUtil;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface IOrderRepo extends MongoRepository<Order, String> {

    //获取订单对象集合
    default Iterable<Order> getOrderList(Iterable<String> orderIDList) {
        return this.findAllById(orderIDList);
    }

    default String insertOrder(String customerID, String goodsID, String customercountry, int count,
                               Date date, String price, String tax, boolean done) {

        String id = UUIDUtil.getShortUuid();
        this.insert(new Order(
                id,
                customerID,
                goodsID,
                customercountry,
                count,
                date,
                price,
                tax,
                done
        ));
        return id;
    }
}

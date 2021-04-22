package com.example.sellersystem.server;

import com.example.sellersystem.apiResponse.AddOrderResp;
import com.example.sellersystem.dao.IGoodsRepo;
import com.example.sellersystem.dao.IOrderRepo;
import com.example.sellersystem.dao.ISellerUserRepo;
import com.example.sellersystem.document.Goods;
import com.example.sellersystem.document.Order;
import com.example.sellersystem.document.SellerUser;
import com.example.sellersystem.utils.DateUtil;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServer {

    private final IOrderRepo orderRepo;
    private final ISellerUserRepo userRepo;
    private final IGoodsRepo goodsRepo;

    @Autowired
    public OrderServer(IOrderRepo orderRepo, ISellerUserRepo userRepo, IGoodsRepo goodsRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.goodsRepo = goodsRepo;
    }

    //1. 获取所有订单
    public Iterable<Order> getUserOrder(String uid) {
        SellerUser sellerUser = userRepo.getUser(uid);
        if (null == sellerUser)
            return null;
        Iterable<String> orderList = sellerUser.getOrderID();
        if (null == orderList)
            return null;
        return orderRepo.getOrderList(orderList);
    }

    //2.  添加订单
    public AddOrderResp addOrder(String uid, String customerID, String customerCountry, String goodsID,
                                 int count, String date, String price, String tax, boolean done) {
        //判断customer是否合法
        if (customerID.equals(""))
            return new AddOrderResp(2, "客户不存在", null);

        //判断user是否合法
        SellerUser sellerUser = userRepo.getUser(uid);
        if (null == sellerUser)
            return new AddOrderResp(1, "用户不存在", null);
        if (!sellerUser.getGoodsID().contains(goodsID))
            return new AddOrderResp(3, "用户没有这个商品", null);

        //判断商品是否可以卖出
        Goods goods = goodsRepo.getGoods(goodsID);
        if (!goods.isUploaded())
            return new AddOrderResp(5, "商品没有上架", null);
        if (!goods.getSellCountries().contains(customerCountry))
            return new AddOrderResp(4, "用户不在可销售国家范围内", null);
        if(goods.getInStock() < count)
            return new AddOrderResp(6, "商品库存不足，仅剩"+ goods.getInStock() + "件！", null);


        String newOrderID = orderRepo.insertOrder(
                customerID,
                customerCountry,
                goodsID,
                count,
                DateUtil.strToDate(date),   //字符串转date
                price,
                tax,
                done
        );

        //在商品中添加记录
        if (goods.getOrderIDListByCountry().containsKey(customerCountry))
            goods.getOrderIDListByCountry().get(customerCountry).add(newOrderID);
        else {
            List<String> list = new ArrayList<>();
            list.add(newOrderID);
            goods.getOrderIDListByCountry().put(customerCountry, list);
        }
        goods.setInStock(goods.getInStock() - count);  //更新库存
        goodsRepo.save(goods);

        //在用户中添加记录
        sellerUser.getOrderID().add(newOrderID);
        userRepo.save(sellerUser);

        return new AddOrderResp(0, "添加成功", newOrderID);
    }


    //3.  找到商品的前三大销售国家
    public List<Pair<String, Long>> getTop3Countries(String goodsID, String begin, String end) {

        /*  前提判定 */
        Goods goods = goodsRepo.getGoods(goodsID);
        if (goods == null)
            return null;
        Map<String, List<String>> orderIDListByCountry = goods.getOrderIDListByCountry();
        if (orderIDListByCountry == null)
            return null;

        /* 开始计算 */
        List<Pair<String, Long>> temp = new ArrayList<>();

        //按照国家分别便利
        for (Map.Entry<String, List<String>> entry : orderIDListByCountry.entrySet()) {
            String country = entry.getKey();
            List<String> orderIDList = entry.getValue();
            long sum = 0;
            Iterable<Order> orders = orderRepo.getOrderList(orderIDList);

            //遍历某一国家中的订单
            for (Order order : orders) {
                //是否在时间范围内
                if (DateUtil.belongCalendar(
                        order.getDate(),
                        DateUtil.strToDate(begin),
                        DateUtil.strToDate(end))
                ) {
                    sum += order.getCount();  //累积到销量中
                }
            }
            Pair pair = new Pair(country, sum);
            temp.add(pair);
        }
        return getAns(temp);
    }

    //始终维持前三
    private List<Pair<String, Long>> getAns(List<Pair<String, Long>> temp) {
        String a = null, b = null, c = null;
        long x = -1, y = -1, z = -1;

        for (Pair<String, Long> p : temp) {
            String country = p.getKey();
            long value = p.getValue();
            if (value >= x) {
                c = b;
                z = y;
                b = a;
                y = x;
                x = value;
                a = country;
            } else if (value >= y) {
                c = b;
                z = y;
                y = value;
                b = country;
            } else if (value > z) {
                z = value;
                c = country;
            }
        }

        List<Pair<String, Long>> res = new ArrayList<>();
        if (a != null) {
            Pair pair1 = new Pair(a, x);
            res.add(pair1);
        }
        if (b != null) {
            Pair pair2 = new Pair(b, y);
            res.add(pair2);
        }
        if (c != null) {
            Pair pair3 = new Pair(c, z);
            res.add(pair3);
        }
        return res;
    }
}

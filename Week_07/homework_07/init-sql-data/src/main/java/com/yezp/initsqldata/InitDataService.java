package com.yezp.initsqldata;

import com.yezp.initsqldata.domain.Goods;
import com.yezp.initsqldata.domain.Order;
import com.yezp.initsqldata.domain.User;
import com.yezp.initsqldata.manager.JdbcManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Description:
 * Created on 2020/11/29 22:42.
 *
 * @author yezp
 */
@Service
@Slf4j
public class InitDataService {

    @Autowired
    private JdbcManager jdbcManager;

    public List<User> initUserData() {
        List<User> userList = new ArrayList<>(100);
        Random random = new Random();
        for (int i = 1; i <= 100; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName("Robot-" + i);
            user.setPhone((1597654321 + i));
            user.setAge(10 + random.nextInt(60) + 1);
            user.setSex(random.nextInt(2));
            user.setCreateTime(System.currentTimeMillis());
            user.setUpdateTime(System.currentTimeMillis());
            userList.add(user);
        }

        return userList;
    }


    public List<Goods> initGoodsData() {
        List<Goods> goodsList = new ArrayList<>(100);
        Random random = new Random();

        for (int i = 1; i <= 10000; i++) {
            Goods goods = new Goods();
            goods.setGoodsId(i);
            goods.setGoodsName("GoodsNo_" + i);
            goods.setDescription("this is goodsNo_" + i);
            goods.setImages("");
            goods.setPrice(10 + random.nextInt(10000) + 1);
            goods.setDecimalPlaces(0);
            goods.setCreateTime(System.currentTimeMillis());
            goods.setUpdateTime(System.currentTimeMillis());
            goodsList.add(goods);
        }

        return goodsList;
    }

    public void initOrderData() {
        List<Order> orderList = new ArrayList<>(1000);
        Random random = new Random();

        for (int i = 1; i <= 1000000; i++) {
            Order order = new Order();
            order.setOrderId(i);
            order.setUserId(random.nextInt(100) + 1);
            order.setStatus(0);
            order.setPrice(10 + random.nextInt(10000) + 1);
            order.setDecimalPlaces(0);
            order.setCreateTime(System.currentTimeMillis());
            order.setUpdateTime(System.currentTimeMillis());
            orderList.add(order);

            if (orderList.size() % 1000 == 0) {
                jdbcManager.addOrderBatch(orderList);
                orderList.clear();
                log.info(" =====================>> " + i);
            }
        }
    }

}

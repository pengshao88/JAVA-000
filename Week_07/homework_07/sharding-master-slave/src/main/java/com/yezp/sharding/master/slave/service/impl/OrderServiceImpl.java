package com.yezp.sharding.master.slave.service.impl;

import com.yezp.sharding.master.slave.dao.OrderDao;
import com.yezp.sharding.master.slave.domain.Order;
import com.yezp.sharding.master.slave.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Description:订单业务表
 * Created on 2020/12/4 0:34.
 *
 * @author yezp
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> getOrderList() {
        return orderDao.getOrderList();
    }

    @Override
    public void createOrder() {
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            Order order = new Order();
            order.setOrderId(i);
            order.setUserId(random.nextInt(100) + 1);
            order.setStatus(0);
            order.setPrice(10 + random.nextInt(10000) + 1);
            order.setDecimalPlaces(0);
            order.setCreateTime(System.currentTimeMillis());
            order.setUpdateTime(System.currentTimeMillis());
            orderDao.createOrder(order);
        }
    }
}

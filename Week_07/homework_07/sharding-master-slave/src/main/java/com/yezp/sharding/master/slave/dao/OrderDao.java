package com.yezp.sharding.master.slave.dao;

import com.yezp.sharding.master.slave.domain.Order;

import java.util.List;

/**
 * Description: 订单表Dao
 * Created on 2020/12/4 0:17.
 *
 * @author yezp
 */
public interface OrderDao {

    List<Order> getOrderList();

    void createOrder(Order order);

}

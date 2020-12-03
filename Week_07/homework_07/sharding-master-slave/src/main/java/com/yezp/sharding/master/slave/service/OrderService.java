package com.yezp.sharding.master.slave.service;

import com.yezp.sharding.master.slave.domain.Order;

import java.util.List;

/**
 * Description:
 * Created on 2020/12/4 0:33.
 *
 * @author yezp
 */
public interface OrderService {

    List<Order> getOrderList();

    void createOrder();

}

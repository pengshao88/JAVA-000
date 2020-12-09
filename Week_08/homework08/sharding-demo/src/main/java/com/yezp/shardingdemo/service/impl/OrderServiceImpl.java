package com.yezp.shardingdemo.service.impl;

import com.yezp.shardingdemo.domain.Order;
import com.yezp.shardingdemo.mybatis.mapper.OrderMapper;
import com.yezp.shardingdemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Description:
 * Created on 2020/12/6 16:11.
 *
 * @author yezp
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Long> insertData() throws SQLException {
        List<Long> result = new ArrayList<>(10);
        Random random = new Random();
        for (long i = 1; i <= 10; i++) {
            Order order = new Order();
//            long orderId = random.nextInt(1000000000) + 1;
//            order.setOrderId(orderId);
            order.setUserId(random.nextInt(100) + 1);
            order.setStatus(0);
            order.setPrice(10 + random.nextInt(10000) + 1);
            order.setDecimalPlaces(0);
            order.setCreateTime(System.currentTimeMillis());
            order.setUpdateTime(System.currentTimeMillis());
            orderMapper.insert(order);
            result.add(i);
        }

        return result;
    }

    @Override
    public void deleteData(List<Long> orderIds)  throws SQLException {
        for (long orderId : orderIds) {
            orderMapper.delete(orderId);
        }
    }

    @Override
    public void updateData(Order order) throws SQLException {
        orderMapper.update(order);
    }

    @Override
    public void printData() throws SQLException {
        System.out.println("---------------------------- Print Order Data -----------------------");
        for (Object each : orderMapper.selectAll()) {
            System.out.println(each);
        }
    }

    @Override
    public List<Order> selectAll() throws SQLException {
        return orderMapper.selectAll();
    }
}

package com.yezp.shardingdemo.service;

import com.yezp.shardingdemo.domain.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * Description:
 * Created on 2020/12/6 16:11.
 *
 * @author yezp
 */
public interface OrderService {

    List<Long> insertData()  throws SQLException;

    void deleteData(final List<Long> orderIds)  throws SQLException;

    void updateData(final Order order) throws SQLException;

    void printData() throws SQLException;

    List<Order> selectAll() throws SQLException;

}

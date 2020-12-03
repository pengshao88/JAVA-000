package com.yezp.sharding.master.slave.dao.impl;

import com.yezp.sharding.master.slave.dao.OrderDao;
import com.yezp.sharding.master.slave.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 * Created on 2020/12/4 0:25.
 *
 * @author yezp
 */
@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Order> getOrderList() {
        String sql = "select id, order_id, user_id, status, price, decimal_places, create_time, update_time from `order` ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));
    }

    @Override
    public void createOrder(Order order) {
        StringBuffer sb = new StringBuffer();
        sb.append("insert into `order`(order_id, user_id, status, price, decimal_places, create_time, update_time)");
        sb.append("values(");
        sb.append(order.getOrderId()).append(",");
        sb.append(order.getUserId()).append(",");
        sb.append(order.getStatus()).append(",");
        sb.append(order.getPrice()).append(",");
        sb.append(order.getDecimalPlaces()).append(",");
        sb.append(order.getCreateTime()).append(",");
        sb.append(order.getUpdateTime());

        sb.append(")");
        jdbcTemplate.update(sb.toString());
    }
}

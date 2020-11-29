package com.yezp.initsqldata.manager;

import com.yezp.initsqldata.domain.Goods;
import com.yezp.initsqldata.domain.Order;
import com.yezp.initsqldata.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
 * Description:
 * Created on 2020/11/29 22:00.
 *
 * @author yezp
 */
@Component
@Slf4j
public class JdbcManager {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void showConnection() throws SQLException {
        log.info(dataSource.toString());
        Connection conn = dataSource.getConnection();
        log.info(conn.toString());
        conn.close();
    }

    public void addUserBatch(List<User> userList) {
        final List<User> tempUserList = userList;
        String sql = "insert into user(id, user_id, user_name, phone, address, age, sex, create_time, update_time)" +
                " values(null, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
            @Override
            public int getBatchSize() {
                return tempUserList.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {
                ps.setLong(1, tempUserList.get(i).getUserId());
                ps.setString(2, tempUserList.get(i).getUserName());
                ps.setInt(3, tempUserList.get(i).getPhone());
                ps.setString(4, tempUserList.get(i).getAddress());
                ps.setInt(5, tempUserList.get(i).getAge());
                ps.setInt(6, tempUserList.get(i).getSex());
                ps.setLong(7, tempUserList.get(i).getCreateTime());
                ps.setLong(8, tempUserList.get(i).getUpdateTime());
            }
        });
    }

    public void addGoodsBatch(List<Goods> goodsList) {
        final List<Goods> tempGoodsList = goodsList;
        String sql = "insert into goods(id, goods_id, goods_name, description, images, price, decimal_places, create_time, update_time)" +
                " values(null, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {
                ps.setLong(1, tempGoodsList.get(i).getGoodsId());
                ps.setString(2, tempGoodsList.get(i).getGoodsName());
                ps.setString(3, tempGoodsList.get(i).getDescription());
                ps.setString(4, tempGoodsList.get(i).getImages());
                ps.setInt(5, tempGoodsList.get(i).getPrice());
                ps.setInt(6, tempGoodsList.get(i).getDecimalPlaces());
                ps.setLong(7, tempGoodsList.get(i).getCreateTime());
                ps.setLong(8, tempGoodsList.get(i).getUpdateTime());
            }

            @Override
            public int getBatchSize() {
                return goodsList.size();
            }
        });
    }

    public void addOrderBatch(List<Order> orderList) {
        final List<Order> tempOrderList = orderList;
        String sql = "insert into `order`(id, order_id, user_id, status, price, decimal_places, create_time, update_time)" +
                " values(null, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {
                ps.setLong(1, tempOrderList.get(i).getOrderId());
                ps.setLong(2, tempOrderList.get(i).getUserId());
                ps.setInt(3, tempOrderList.get(i).getStatus());
                ps.setInt(4, tempOrderList.get(i).getPrice());
                ps.setInt(5, tempOrderList.get(i).getDecimalPlaces());
                ps.setLong(6, tempOrderList.get(i).getCreateTime());
                ps.setLong(7, tempOrderList.get(i).getUpdateTime());
            }

            @Override
            public int getBatchSize() {
                return orderList.size();
            }
        });
    }

    public void addBatchOrder() {
        try {
            Connection conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement cmd = conn.prepareStatement("insert into `order_copy`(id, order_id, user_id, status, price, decimal_places, create_time, update_time)" +
                    " values(null, ?, ?, ?, ?, ?, ?, ?)");

            Random random = new Random();
            long start = System.currentTimeMillis();
            for (int i = 1; i <= 1000000; i++) {//100万条数据
                cmd.setInt(1, i);
                cmd.setLong(2, random.nextInt(100) + 1);
                cmd.setInt(3, 0);
                cmd.setInt(4, 10 + random.nextInt(10000) + 1);
                cmd.setInt(5, 0);
                cmd.setLong(6, System.currentTimeMillis());
                cmd.setLong(7, System.currentTimeMillis());

                cmd.addBatch();
                if (i % 1000 == 0) {
                    cmd.executeBatch();
                    log.info(" =====================>> " + i);
                }
            }
            cmd.executeBatch();
            conn.commit();

            cmd.close();
            conn.close();
            long end = System.currentTimeMillis();
            System.out.println("批量插入需要时间:" + (end - start));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

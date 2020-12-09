package com.yezp.shardingdemo.mybatis.mapper;

import com.yezp.shardingdemo.domain.Order;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

/**
 * Description:
 * Created on 2020/12/6 15:41.
 *
 * @author yezp
 */
@Mapper
public interface OrderMapper {

    /**
     * insert data.
     *
     * @param entity entity
     * @return generated primary key
     * @throws SQLException SQL exception
     */
    void insert(Order entity) throws SQLException;

    /**
     * update data.
     *
     * @param entity entity
     * @return generated primary key
     * @throws SQLException SQL exception
     */
    void update(Order entity) throws SQLException;

    /**
     * Delete data.
     *
     * @param primaryKey primaryKey
     * @throws SQLException SQL exception
     */
    void delete(long primaryKey) throws SQLException;

    /**
     * Select all data.
     *
     * @return all data
     * @throws SQLException SQL exception
     */
    List<Order> selectAll() throws SQLException;

}

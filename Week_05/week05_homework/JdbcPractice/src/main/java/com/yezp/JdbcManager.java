package com.yezp;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Description:
 * Created on 2020/11/15 22:03.
 *
 * @author yezp
 */
@Component("jdbcManager")
public class JdbcManager {

    private Connection conn;

    public Connection getConn() {
        if (conn == null) {
            initJdbcConnection();
        }

        return conn;
    }

    public void initJdbcConnection() {
        String driverClassName = "com.mysql.jdbc.Driver";	//启动驱动
        String url = "jdbc:mysql://localhost:3306/test";	//设置连接路径
        String username = "root";	//数据库用户名
        String password = "";	//数据库连接密码

        try {
            Class.forName(driverClassName); //执行驱动
            conn = DriverManager.getConnection(url, username, password); //获取连接
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (conn == null) {
            return;
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

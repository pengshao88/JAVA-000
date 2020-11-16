package com.yezp.Dao;

import com.yezp.JdbcManager;
import com.yezp.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created on 2020/11/15 22:03.
 *
 * @author yezp
 */
@Component("userDao")
public class UserDao {

    @Autowired
    private JdbcManager jdbcManager;

    public boolean addUser(User user) {
        PreparedStatement pstmt;
        String sql = "INSERT INTO USER VALUES(?, ?, ?)"; //设置的预编译语句格式

        try {
            pstmt = jdbcManager.getConn().prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setInt(3, user.getAge());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean updateUser(User user) {
        PreparedStatement pstmt = null;
        String sql = "UPDATE USER SET `NAME` = ?, AGE = ? WHERE ID = ?";

        try {
            pstmt = jdbcManager.getConn().prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setInt(2, user.getAge());
            pstmt.setInt(3, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean deleteUser(int id) {
        PreparedStatement pstmt;
        String sql = "DELETE FROM USER WHERE ID = ?";

        try {
            pstmt = jdbcManager.getConn().prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public User findUserByName(String name) {
        PreparedStatement pstmt;
        ResultSet rs;

        try {
            String sql = "SELECT * FROM USER WHERE name=?";
            pstmt = jdbcManager.getConn().prepareStatement(sql);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();
            if(rs == null) {
                return null;
            }
            if(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUserList() {
        Statement statement;
        List<User> userList = new ArrayList<>();

        try {
            statement = jdbcManager.getConn().createStatement();
            String sql = "SELECT id, name, age FROM USER";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                //获取字符串类型的值
                String name = resultSet.getString("name");
                //获取int型的值
                int age = resultSet.getInt("age");
                User user = new User(id, name, age);
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean addUser(int id, String name, int age) {
        Statement statement;

        try {
            statement = jdbcManager.getConn().createStatement();
            String sql = "INSERT INTO USER (ID, `NAME`, AGE) VALUE (" + id + ",'" + name + "'," + age +")";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateUser(int id, String name, int age) {
        Statement statement;

        try {
            statement = jdbcManager.getConn().createStatement();
            String sql = "UPDATE USER SET `NAME` = '" + name + "', AGE = " + age + " WHERE ID = " + id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteUserBy(int id) {
        Statement statement;
        String sql = "DELETE FROM USER WHERE ID = " + id;

        try {
            statement = jdbcManager.getConn().createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean batchAddUser(List<User> userList) {
        try {
            Connection connection = jdbcManager.getConn();
            Statement statement = connection.createStatement();
            for (User user : userList) {
                String sql = "INSERT INTO USER (ID, `NAME`, AGE) VALUE ("
                        + user.getId() + ",'" + user.getName() + "'," + user.getAge() + ")";
                statement.addBatch(sql);
            }

            // 开启事务
            connection.setAutoCommit(false);
            // 批处理
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}

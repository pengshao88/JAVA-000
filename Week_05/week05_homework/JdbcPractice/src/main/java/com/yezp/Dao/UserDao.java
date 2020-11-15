package com.yezp.Dao;

import com.yezp.JdbcManager;
import com.yezp.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        PreparedStatement pstmt = null;
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
        PreparedStatement pstmt = null;
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
        PreparedStatement pstmt = null;
        ResultSet rs = null;

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

}

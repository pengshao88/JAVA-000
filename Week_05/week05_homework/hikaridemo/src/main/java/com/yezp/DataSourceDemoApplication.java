package com.yezp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Description:
 * Created on 2020/11/16 21:26.
 *
 * @author yezp
 */
@SpringBootApplication
@Slf4j
public class DataSourceDemoApplication implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DataSourceDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        showConnection();
        showData();

        updateData(1, "stephen", 29);
        addData(10, "ye", 30);
        showData();

        deleteData(10);
        showData();
    }

    private void showConnection() throws SQLException {
        log.info(dataSource.toString());
        Connection conn = dataSource.getConnection();
        log.info(conn.toString());
        conn.close();
    }

    private void addData(int id, String name, int age) {
        String sql = "INSERT INTO USER (ID, `NAME`, AGE) VALUE ("
                + id + ",'" + name + "'," + age + ")";
        jdbcTemplate.execute(sql);
    }

    private void updateData(int id, String name, int age) {
        String sql = "UPDATE USER SET `NAME` = '" + name + "', AGE = " + age + " WHERE ID = " + id;
        jdbcTemplate.execute(sql);
    }

    private void deleteData(int id) {
        String sql = "DELETE FROM USER WHERE ID = " + id;
        jdbcTemplate.execute(sql);
    }


    private void showData() {
        jdbcTemplate.queryForList("SELECT * FROM USER")
                .forEach(row -> log.info(row.toString()));
    }
}

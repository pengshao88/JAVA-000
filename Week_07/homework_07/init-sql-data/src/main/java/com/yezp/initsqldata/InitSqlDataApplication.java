package com.yezp.initsqldata;

import com.yezp.initsqldata.domain.Goods;
import com.yezp.initsqldata.domain.User;
import com.yezp.initsqldata.manager.JdbcManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class InitSqlDataApplication implements CommandLineRunner {

    @Autowired
    private JdbcManager jdbcManager;

    @Autowired
    private InitDataService initDataService;

    public static void main(String[] args) {
        SpringApplication.run(InitSqlDataApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // 初始化用户数据
//        List<User> userList = initDataService.initUserData();
//        jdbcManager.addUserBatch(userList);

        long startTime = System.currentTimeMillis();
        log.info("start insert : " + startTime);
//        // 初始化商品数据
//        List<Goods> goodsList = initDataService.initGoodsData();
//        jdbcManager.addGoodsBatch(goodsList);
//        log.info("end init goods use : " + (System.currentTimeMillis() - startTime));

        // 插入100万条订单数据 ======> 跑了10分钟才插入39万条数据
//        initDataService.initOrderData();

        jdbcManager.addBatchOrder(); // ======> use 94520ms
                                     // ======> 加上rewriteBatchedStatements=true后 use 59354ms
        log.info("end insert use : " + (System.currentTimeMillis() - startTime));
    }
}

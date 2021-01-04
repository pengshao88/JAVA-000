package com.yezp.redis;

import com.yezp.redis.lock.RedisLock;
import com.yezp.redis.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootApplication
public class RedisLockApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(RedisLockApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RedisLockApplication.class, args);
    }

    @Autowired
    RedisLock redisLock;

    int count = 0;

    @Override
    public void run(String... args) throws Exception {
        int clientCount =1000;
        CountDownLatch countDownLatch = new CountDownLatch(clientCount);

        ExecutorService executorService = Executors.newFixedThreadPool(clientCount);
        long start = System.currentTimeMillis();
        for (int i = 0;i<clientCount;i++){
            executorService.execute(() -> {

                //通过Snowflake算法获取唯一的ID字符串
                String id = String.valueOf(IdUtil.generateId());
                try {
                    redisLock.lock(id);
                    count++;
                }finally {
                    redisLock.unlock(id);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        logger.info("执行线程数:{},总耗时:{},count数为:{}", clientCount, end - start, count);
    }
}

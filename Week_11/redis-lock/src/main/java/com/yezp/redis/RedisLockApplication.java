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
import java.util.concurrent.atomic.AtomicInteger;


@SpringBootApplication
public class RedisLockApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(RedisLockApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RedisLockApplication.class, args);
    }

    @Autowired
    RedisLock redisLock;

    int count = 100;

    @Override
    public void run(String... args) throws Exception {
        int clientCount = 200;
//        CountDownLatch countDownLatch = new CountDownLatch(clientCount);

        ExecutorService executorService = Executors.newFixedThreadPool(clientCount);
//        long start = System.currentTimeMillis();
        // 通过Snowflake算法获取唯一的ID字符串
        final String id = String.valueOf(IdUtil.generateId());
        for (int i = 0; i < clientCount; i++) {
            executorService.execute(() -> {
                try {
                    if (redisLock.lock(id)) {
                        if (count <= 0)
                            return;

                        count--;
                        logger.info("count:{}", count);
                    }
                } finally {
                    redisLock.unlock(id);
                }
//                countDownLatch.countDown();
            });
        }
//        countDownLatch.await();
//        long end = System.currentTimeMillis();
//        logger.info("执行线程数:{},总耗时:{},count数为:{}", clientCount, end - start, count);
    }
}

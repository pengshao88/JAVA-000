package com.yezp.redis.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * Description: redis分布式锁
 * Created on 2021/1/4 23:15.
 *
 * @author yezp
 */
@Component
public class RedisLock {

    private Logger logger = LoggerFactory.getLogger(Logger.class);

    // 锁键
    private String LOCK_KEY = "redis_lock";

    // 锁过期时间
    protected long internalLockLeaseTime = 30000;

    // 获取锁的超时时间
    private long timeOut = 1000;

    // SET命令的参数
    SetParams params = SetParams.setParams().nx().px(internalLockLeaseTime);

    @Autowired
    JedisPool jedisPool;

    /**
     * 加锁
     *
     * @param id
     * @return
     */
    public boolean lock(String id) {
        Jedis jedis = jedisPool.getResource();
        long start = System.currentTimeMillis();

        try {
            for (;;) {
                // SET命令返回OK，则证明获取锁成功
                String lock = jedis.set(LOCK_KEY, id, params);
                if ("OK".equals(lock)) {
                    return true;
                }

                // 否则循环等待，在timeOut时间内仍未获取到锁，则获取失败
                long l = System.currentTimeMillis() - start;
                if (l >= timeOut) {
                    return false;
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    logger.error("Thread.sleep error", e);
                }

            }
        } finally {
            jedis.close();
        }
    }

    /**
     * 解锁，执行lua脚本
     *
     * @param id
     * @return
     */
    public boolean unlock(String id) {
        Jedis jedis = jedisPool.getResource();
        String script =
                "if redis.call('get', KEYS[1]) == ARGV[1] " +
                "then" +
                "   return redis.call('del', KEYS[1]) " +
                "else" +
                "   return 0 " +
                "end";

        try {
            Object result = jedis.eval(script, Collections.singletonList(LOCK_KEY), Collections.singletonList(id));
            if ("1".equals(result.toString())) {
                return true;
            }

            return false;
        } finally {
            jedis.close();
        }
    }

}

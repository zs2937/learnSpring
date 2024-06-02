package com.jirengu.distributionLock.lock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Service
public class MyDistributionLock {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void lock(String key, String value, long expireTime) {
        // 自旋, 直到获取锁
        while (true) {
            System.out.println("尝试获取分布式锁");
            if (Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, TimeUnit.MILLISECONDS))) {
                System.out.println("获取分布式锁-成功");
                return;
            } else {
                System.out.println("获取分布式锁-失败");
            }
            // 阻塞等待一段时间
            LockSupport.parkNanos(this, TimeUnit.MILLISECONDS.toNanos(1000));
        }
    }

    public boolean tryLock(String key, String value, long expireTime) {
        System.out.println("尝试获取分布式锁");
        if (Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, TimeUnit.MILLISECONDS))) {
            System.out.println("获取分布式锁-成功");
            return true;
        } else {
            System.out.println("获取分布式锁-失败");
            return false;
        }
    }

    public boolean tryLock(String key, String value, long expireTime, long waitTime) {
        long start = System.currentTimeMillis();
        // 自旋, 直到超过等待时长
        while (true) {
            System.out.println("尝试获取分布式锁");
            if (Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, TimeUnit.MILLISECONDS))) {
                System.out.println("获取分布式锁-成功");
                return true;
            } else {
                System.out.println("获取分布式锁-失败，等待下一次获取");
            }
            if (System.currentTimeMillis() - start > waitTime) {
                System.out.println("超过等待时长，获取分布式锁-失败");
                return false;
            }
            // 阻塞等待一段时间
            LockSupport.parkNanos(this, TimeUnit.MILLISECONDS.toNanos(1000));
        }
    }

    public void unlock(String key, String value) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        Long result = redisTemplate.execute(redisScript, Collections.singletonList(key), value);
        if (Objects.equals(1L, result)) {
            System.out.println("释放锁成功");
        } else {
            System.out.println("释放锁失败");
        }
    }

}

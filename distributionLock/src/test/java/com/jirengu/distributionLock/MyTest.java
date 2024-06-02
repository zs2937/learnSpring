package com.jirengu.distributionLock;

import com.jirengu.distributionLock.lock.MyDistributionLock;
import com.jirengu.distributionLock.service.MyService;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.UUID;

public class MyTest extends BaseTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private MyDistributionLock myDistributionLock;

    @Resource
    private MyService myService;

    @Test
    public void test1() {
        // 写数据
        redisTemplate.opsForValue().set("key5", 18);
        // 读数据
        Object value = redisTemplate.opsForValue().get("key5");
        System.out.println(value.toString());
    }

    @Test
    public void testLock() {
        String key = "lockKey";
        String value = UUID.randomUUID().toString();
        myDistributionLock.lock(key, value, 100 * 1000);
    }

    @Test
    public void testTryLock1() {
        String key = "key1";
        String value = UUID.randomUUID().toString();
        boolean result = myDistributionLock.tryLock(key, value, 10 * 1000);
        System.out.println("第1次加锁结果 = " + result);
        result = myDistributionLock.tryLock("tryLock1", value, 30 * 1000);
        System.out.println("第2次加锁结果 = " + result);
    }

    @Test
    public void testTryLock2() {
        String key = "tryLock2";
        String value = UUID.randomUUID().toString();
        // 先加锁
        myDistributionLock.lock(key, value, 10 * 1000);
        // 尝试第1次获取锁
        boolean result = myDistributionLock.tryLock(key, value, 10 * 1000, 5 * 1000);
        System.out.println("第1次加锁结果 = " + result);
        result = myDistributionLock.tryLock(key, value, 10 * 1000, 10 * 1000);
        System.out.println("第2次加锁结果 = " + result);
    }

    @Test
    public void testUnlock() {
        String key = "unlock";
        String value = UUID.randomUUID().toString();
        // 加锁
        myDistributionLock.lock(key, value, 30 * 1000);
        // 第一次解锁，预期不成功
        myDistributionLock.unlock(key, UUID.randomUUID().toString());
        // 第二次解锁，预期成功
        myDistributionLock.unlock(key, value);
    }

    @Test
    public void testService() {
        myService.execute("abc",123L);
    }
}

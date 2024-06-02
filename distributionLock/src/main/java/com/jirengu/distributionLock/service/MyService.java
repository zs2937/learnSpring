package com.jirengu.distributionLock.service;

import com.jirengu.distributionLock.annotation.DistributionLockAnnotation;
import com.jirengu.distributionLock.lock.MyDistributionLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class MyService {

    @Resource
    private MyDistributionLock myDistributionLock;

    public void doBusiness(long orderId) {
        // 获取分布式锁
        String value = UUID.randomUUID().toString();
        myDistributionLock.lock(String.valueOf(orderId), value, 10 * 1000);
        // 获取到分布式锁
        // 执行业务逻辑
        System.out.println("执行业务逻辑");
        // 解锁
        myDistributionLock.unlock(String.valueOf(orderId), value);
    }

    public void doBusiness2(long orderId) {
        // 获取分布式锁
        String value = UUID.randomUUID().toString();
        if (myDistributionLock.tryLock(String.valueOf(orderId), value, 10 * 1000)) {
            // 获取到分布式锁
            // 执行业务逻辑
            System.out.println("执行业务逻辑");
            // 解锁
            myDistributionLock.unlock(String.valueOf(orderId), value);
        } else {
            System.out.println("未获取到分布式锁，无法执行业务逻辑");
        }
    }

    public void doBusiness3(long orderId) {
        // 获取分布式锁
        String value = UUID.randomUUID().toString();
        if (myDistributionLock.tryLock(String.valueOf(orderId), value, 10 * 1000, 10 * 1000)) {
            // 获取到分布式锁
            // 执行业务逻辑
            System.out.println("执行业务逻辑");
            // 解锁
            myDistributionLock.unlock(String.valueOf(orderId), value);
        } else {
            System.out.println("未获取到分布式锁，无法执行业务逻辑");
        }
    }

    @DistributionLockAnnotation(key = "#orderId", expireTime = 20000, waitTime = 3000)
    public void execute(String str, long orderId) {
        System.out.println("执行 execute 方法");
    }

}

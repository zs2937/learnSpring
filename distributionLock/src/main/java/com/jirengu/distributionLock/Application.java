package com.jirengu.distributionLock;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
//        // 配置连接信息
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        // 读数据
//        String value = jedis.get("key1");
//        System.out.println(value);
//        // 写数据
//        jedis.set("key2", "v2");
//        // 写数据（设置过期时间）
//        jedis.setex("key3", 10, "v3");

        // 配置连接池参数
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        config.setMaxTotal(18);
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
        // 获取连接
        Jedis jedis = jedisPool.getResource();
        // 读数据
        String value = jedis.get("key1");
        System.out.println(value);
        // 写数据
        jedis.set("key4", "v4");
        // 将连接归还连接池
        jedis.close();
        // 关闭连接池
        jedisPool.close();
    }
}

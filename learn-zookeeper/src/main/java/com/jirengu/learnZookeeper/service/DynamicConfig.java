package com.jirengu.learnZookeeper.service;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DynamicConfig implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(DynamicConfig.class);
    @Resource
    private CuratorFramework zkClient;
    private static final String ROOT_PATH = "/dynamicConfig/learnZk";
    private Map<String, String> configMap = new HashMap<>();
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("=========== init zk started ============");
        // 初始化
        // 获取 ROOT_PATH 的子节点
        List<String> children = zkClient.getChildren().forPath(ROOT_PATH);
        logger.info("=========== children is {}", children);
        // 读取配置项的值并保存到 configMap
        for (String key : children) {
            String path = ROOT_PATH + "/" + key;
            byte[] data = zkClient.getData().forPath(path);
            configMap.put(path, new String(data));
        }
        logger.info("=========== init configMap is {}", configMap);
        // 保证实时性，利用zk的watch机制
        CuratorCache curatorCache = CuratorCache.build(zkClient, ROOT_PATH);
        curatorCache.start();
        // 创建监听器
        curatorCache.listenable().addListener((type, oldData, newData) -> {
            switch (type) {
                case NODE_CHANGED:
                    // 子节点数据被更新
                    byte[] byteData = newData.getData();
                    String key = newData.getPath();
                    String value = new String(byteData);
                    String oldValue = new String(oldData.getData());
                    logger.info("========= Data change, key : {}, oldValue : {}, newValue : {}", key, oldValue, value);
                    configMap.put(key, value);
                    break;
                default:
                    break;
            }
        });
    }

    public String getConfigValue(String key) {
        String path = ROOT_PATH + "/" + key;
        return configMap.get(path);
    }
}

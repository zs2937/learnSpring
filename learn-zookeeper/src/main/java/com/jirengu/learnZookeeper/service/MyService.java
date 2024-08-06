package com.jirengu.learnZookeeper.service;

import com.jirengu.learnZookeeper.config.ZooKeeperConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyService {

    @Resource
    private CuratorFramework zkClient;

    public void create(String path, String data) throws Exception {
        byte[] payload = data.getBytes();
        zkClient.create().withMode(CreateMode.PERSISTENT).forPath(path, payload);
    }

}

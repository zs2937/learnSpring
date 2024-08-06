package com.jirengu.learnZookeeper.service;

import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class MyServiceTest extends BaseTest {

    @Resource
    private MyService myService;

    @Test
    public void create() throws Exception {
        String path = "/zkTest/dataNode";
        String data = "123456";
        myService.create(path, data);
    }
}
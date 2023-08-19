package com.jirengu.spring.ioc.advance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Orange {
    @Value("橙子")
    private String name;
    @Resource
    private Watermelon waterMelon;


    public Orange() {
        System.out.println("Orange 实例化");
    }
}

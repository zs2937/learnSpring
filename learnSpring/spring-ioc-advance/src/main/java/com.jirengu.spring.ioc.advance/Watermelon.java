package com.jirengu.spring.ioc.advance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Watermelon {
    @Value("西瓜")
    private String name;
    @Resource
    private Orange orange;


    public Watermelon() {
        System.out.println("Watermelon 实例化");
    }
}

package com.jirengu.spring.ioc.advance;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

//@Component("apple")
public class AppleFactory implements FactoryBean<Apple> {
    @Override
    public Apple getObject() throws Exception {
        System.out.println("AppleFactory 的 getObject 被调用");
        return new Apple();
    }

    @Override
    public Class<?> getObjectType() {
        return Apple.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}

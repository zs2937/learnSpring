package com.jirengu.spring.introduction.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIoCTest {

    @Test
    public void testGetBeanXml() {
        // 获取 Spring IoC 容器对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        // 从 Spring IoC 容器对象中获取 bean
        Object object = applicationContext.getBean("user");
        System.out.println(object);
    }
}

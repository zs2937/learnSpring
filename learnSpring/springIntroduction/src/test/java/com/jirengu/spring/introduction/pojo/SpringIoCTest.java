package com.jirengu.spring.introduction.pojo;

import com.jirengu.spring.introduction.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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

    @Test
    public void testGetBeanAnnotation() {
        // 获取 Spring IoC 容器对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean2.xml");
        // 从 Spring IoC 容器对象中获取 bean
        Object object = applicationContext.getBean("user");
        System.out.println(object);
    }

    @Test
    public void testGetBeanAllAnnotation() {
        // 获取 Spring IoC 容器对象
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 从 Spring IoC 容器对象中获取 bean
        Object object = applicationContext.getBean("user");
        System.out.println(object);
    }
}

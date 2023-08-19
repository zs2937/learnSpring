package com.jirengu.spring.ioc.advance;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoCTest {

    @Test
    public void testSingleton() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Apple apple1 = applicationContext.getBean("apple", Apple.class);
        System.out.println("第 1 个 bean：" + apple1);
        Apple apple2 = applicationContext.getBean("apple", Apple.class);
        System.out.println("第 2 个 bean：" + apple2);
    }

    @Test
    public void testFactoryBean() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Object apple1 = applicationContext.getBean("&apple");
        System.out.println("第 1 个 bean：" + apple1);
        Object apple2 = applicationContext.getBean("apple");
        System.out.println("第 2 个 bean：" + apple2);
    }

    @Test
    public void testBeanLifeCycle5() {
        // 要在单元测试模拟包含销毁在内的 Spring bean 完成的生命周期，需要手动关闭 Spring IoC 容器，ClassPathXmlApplicationContext 支持手动关闭
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Banana banana = applicationContext.getBean("banana", Banana.class);
        System.out.println("使用bean：" + banana);
        applicationContext.close();
    }

    @Test
    public void testCycleDependency() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Orange orange = applicationContext.getBean("orange", Orange.class);
        Watermelon waterMelon = applicationContext.getBean("watermelon", Watermelon.class);
        System.out.println(orange);
        System.out.println(waterMelon);
    }

}
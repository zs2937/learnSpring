package com.jirengu.spring.ioc.advance;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Banana implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware, BeanClassLoaderAware {
    @Value("香蕉")
    private String name;
    public Banana() {
        System.out.println("实例化");
    }

    @Override
    public String toString() {
        return "Banana{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化: " + this.name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("setBeanClassLoader 被调用 " + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory 被调用 " + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName 被调用 " + name + this.name);
    }
}

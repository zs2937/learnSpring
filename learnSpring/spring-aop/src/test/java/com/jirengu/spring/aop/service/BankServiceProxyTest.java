package com.jirengu.spring.aop.service;

import com.jirengu.spring.aop.configuration.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class BankServiceProxyTest {

    @Test
    public void testProxy() {
        // 配置代理
        BankService bankService = new BankService();
        BankServiceProxy bankServiceProxy = new BankServiceProxy(bankService);
        // 执行方法
        bankServiceProxy.inquiryBalance(12);
        bankServiceProxy.saveMoney(1, 1000);
        bankServiceProxy.withdrawMoney(3, 2000);
    }

    @Test
    public void testSpringAOP() {
        // 获取 Spring IoC 容器对象
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 获取 bean
        MyBankService myBankService = applicationContext.getBean("myBankService", MyBankService.class);
        // 执行方法
        myBankService.inquiryBalance(12);
        myBankService.saveMoney(1, 1000);
        myBankService.withdrawMoney(2, 1234);
    }

}
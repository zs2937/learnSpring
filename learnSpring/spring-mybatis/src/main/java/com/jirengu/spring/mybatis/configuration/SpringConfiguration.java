package com.jirengu.spring.mybatis.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.jirengu.spring.mybatis")
@Import({DataSourceConfig.class, MyBatisConfiguration.class})
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
public class SpringConfiguration {

}

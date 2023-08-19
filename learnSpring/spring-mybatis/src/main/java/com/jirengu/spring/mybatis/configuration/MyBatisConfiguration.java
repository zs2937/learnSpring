package com.jirengu.spring.mybatis.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

public class MyBatisConfiguration {

    /**
     * 定义bean：SqlSessionFactoryBean，用于产生SqlSessionFactory对象
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(@Autowired DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置模型类的别名扫描
        sqlSessionFactoryBean.setTypeAliasesPackage("com.jirengu.spring.mybatis.pojo");
        //设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    /**
     * 定义bean，返回 MapperScannerConfigurer 对象
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.jirengu.spring.mybatis.mapper");
        return msc;
    }


    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(@Autowired DruidDataSource dataSource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }

}

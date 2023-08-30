package com.jirengu.springboot;

import com.jirengu.springboot.mapper.AccountPOMapper;
import com.jirengu.springboot.pojo.AccountPO;
import org.junit.Test;

import javax.annotation.Resource;

public class MyTest extends BaseTest {

    @Resource
    private AccountPOMapper accountPOMapper;

    @Test
    public void test() {
        AccountPO accountPO = accountPOMapper.selectByPrimaryKey(1);
        System.out.println(accountPO);
    }

}

package com.jirengu.spring.mybatis.service;

import com.jirengu.spring.mybatis.mapper.AccountPOMapper;
import com.jirengu.spring.mybatis.pojo.AccountPO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class InnerService {

    @Resource
    private AccountPOMapper accountPOMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void innerMethod() {
        // 给 id = 1 的账户 + 10
        AccountPO accountPO = accountPOMapper.selectByPrimaryKey(1);
        accountPO.setMoney(accountPO.getMoney() + 10);
        accountPOMapper.updateByPrimaryKey(accountPO);
        int a = 1/0;
        // 给 id = 2 的账户 + 10
        accountPO = accountPOMapper.selectByPrimaryKey(2);
        accountPO.setMoney(accountPO.getMoney() + 10);
        accountPOMapper.updateByPrimaryKey(accountPO);
    }
}

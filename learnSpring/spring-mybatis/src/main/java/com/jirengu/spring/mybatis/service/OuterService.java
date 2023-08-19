package com.jirengu.spring.mybatis.service;

import com.jirengu.spring.mybatis.mapper.AccountPOMapper;
import com.jirengu.spring.mybatis.pojo.AccountPO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OuterService {

    @Resource
    private InnerService innerService;

    @Resource
    private AccountPOMapper accountPOMapper;

    @Transactional
    public void outerMethod() {
        // 给 id = 3 的账户 + 10
        AccountPO accountPO = accountPOMapper.selectByPrimaryKey(3);
        accountPO.setMoney(accountPO.getMoney() + 10);
        accountPOMapper.updateByPrimaryKey(accountPO);
        // 调用 inner 方法
        try {
            innerService.innerMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 给 id = 4 的账户 + 10
        accountPO = accountPOMapper.selectByPrimaryKey(4);
        accountPO.setMoney(accountPO.getMoney() + 10);
        accountPOMapper.updateByPrimaryKey(accountPO);
    }
}

package com.jirengu.spring.mybatis.service;

import com.jirengu.spring.mybatis.mapper.AccountPOMapper;
import com.jirengu.spring.mybatis.pojo.AccountPO;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class AccountService implements IAccountService {
    @Resource
    private AccountPOMapper accountPOMapper;
    @Override
    public Integer balanceInquiry(int id) {
        AccountPO accountPO = accountPOMapper.selectByPrimaryKey(id);
        if (accountPO == null) {
            return null;
        } else {
            return accountPO.getMoney();
        }
    }
    @Override
    @Transactional
    public boolean transferMoney(int fromAccountId, int toAccountId, int money) {
        if (money <= 0) {
            // 转账金额不能小于等于0
            return false;
        }
        Integer fromAccountBalance = balanceInquiry(fromAccountId);
        if (fromAccountBalance == null) {
            return false;
        }
        Integer toAccountBalance = balanceInquiry(toAccountId);
        if (toAccountBalance == null) {
            return false;
        }
        if (money > fromAccountBalance) {
            // 转账金额大于余额
            return false;
        }
        // 扣减转出账户余额
        AccountPO fromAccount = new AccountPO();
        fromAccount.setId(fromAccountId);
        fromAccount.setMoney(fromAccountBalance - money);
        int fromCount = accountPOMapper.updateByPrimaryKeySelective(fromAccount);
        int a = 1/0;
        // 增加转入账户余额
        AccountPO toAccount = new AccountPO();
        toAccount.setId(toAccountId);
        toAccount.setMoney(toAccountBalance + money);
        int toCount = accountPOMapper.updateByPrimaryKeySelective(toAccount);
        if (fromCount + toCount == 2) {
            return true;
        } else {
            throw new RuntimeException("转账异常");
        }
    }

    @Override
    public boolean transferMoneyWrapper(int fromAccountId, int toAccountId, int money) {
        return ((IAccountService) AopContext.currentProxy()).transferMoney(fromAccountId, toAccountId, money);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMoney(int accountId, int money) throws IOException {
        Integer balance = balanceInquiry(accountId);
        AccountPO fromAccount = new AccountPO();
        fromAccount.setId(accountId);
        fromAccount.setMoney(balance + money);
        accountPOMapper.updateByPrimaryKeySelective(fromAccount);
        throw new IOException();
    }
}

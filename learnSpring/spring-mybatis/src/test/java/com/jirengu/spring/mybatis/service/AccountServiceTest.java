package com.jirengu.spring.mybatis.service;

import org.junit.Test;

import javax.annotation.Resource;

public class AccountServiceTest extends BaseTest {
    @Resource
    private IAccountService accountService;
    @Resource
    private OuterService outerService;
    @Test
    public void testBalanceInquiry() {
        int id = 1;
        Integer balance = accountService.balanceInquiry(id);
        String msg = String.format("balance of id %d id %d", id, balance);
        System.out.println(msg);
    }

    @Test
    public void testTransferMoney() {
        int fromAccountId = 2;
        int toAccountId = 1;
        boolean success = accountService.transferMoney(fromAccountId, toAccountId, 10);
        String msg = success ? "成功" : "失败";
        System.out.println("转账" + msg);
    }

    @Test
    public void testTransferMoneyWrapper() {
        int fromAccountId = 2;
        int toAccountId = 1;
        boolean success = accountService.transferMoneyWrapper(fromAccountId, toAccountId, 10);
        String msg = success ? "成功" : "失败";
        System.out.println("转账" + msg);
    }

    @Test
    public void testTransactionalRollbackFor() throws Exception {
        int accountId = 1;
        accountService.saveMoney(accountId, 10);
    }

    @Test
    public void testPropagation() throws Exception {
        outerService.outerMethod();
    }
}
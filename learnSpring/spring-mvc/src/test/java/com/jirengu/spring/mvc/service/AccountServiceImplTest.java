package com.jirengu.spring.mvc.service;

import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class AccountServiceImplTest extends BaseTest {

    @Resource
    private IAccountService accountService;

    @Test
    public void balanceInquiry() {
        Integer balance = accountService.balanceInquiry(1);
        System.out.println(balance);
    }
}
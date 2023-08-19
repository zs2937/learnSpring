package com.jirengu.spring.mybatis.service;

public interface IAccountService {

    Integer balanceInquiry(int id);

    boolean transferMoney(int fromAccountId, int toAccountId, int money);

    boolean transferMoneyWrapper(int fromAccountId, int toAccountId, int money);

}

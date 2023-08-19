package com.jirengu.spring.mybatis.service;

import java.io.IOException;

public interface IAccountService {

    Integer balanceInquiry(int id);

    boolean transferMoney(int fromAccountId, int toAccountId, int money);

    boolean transferMoneyWrapper(int fromAccountId, int toAccountId, int money);

    void saveMoney(int accountId, int money) throws IOException;

}

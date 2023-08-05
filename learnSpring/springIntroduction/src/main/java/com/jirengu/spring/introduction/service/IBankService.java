package com.jirengu.spring.introduction.service;

import com.jirengu.spring.introduction.pojo.BankOperationResult;

public interface IBankService {

    BankOperationResult balanceInquiry(int id);

    BankOperationResult saveMoney(int id, int money);

    BankOperationResult withdrawMoney(int id, int money);

}

package com.jirengu.spring.mvc.service;

import com.jirengu.spring.mvc.excpetion.ParamException;
import com.jirengu.spring.mvc.pojo.AccountPO;
import com.jirengu.spring.mvc.request.TransferMoneyRequest;

public interface IAccountService {
    Integer balanceInquiry(int id);

    AccountPO queryAccount(int id);

    void transferMoney(TransferMoneyRequest request) throws ParamException;

}

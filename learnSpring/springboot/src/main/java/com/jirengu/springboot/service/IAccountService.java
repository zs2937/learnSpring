package com.jirengu.springboot.service;

import com.jirengu.springboot.excpetion.BusinessException;
import com.jirengu.springboot.excpetion.ParamException;
import com.jirengu.springboot.pojo.AccountPO;
import com.jirengu.springboot.request.TransferMoneyRequest;

public interface IAccountService {

    AccountPO queryAccountById(int id);

    void transferMoney(TransferMoneyRequest request) throws ParamException, BusinessException;

}

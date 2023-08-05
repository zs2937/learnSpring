package com.jirengu.spring.introduction.dao;

import com.jirengu.spring.introduction.pojo.AccountPO;

public interface IBankDao {

    AccountPO selectById(int id);

    int updateAccount(AccountPO accountPO);

}

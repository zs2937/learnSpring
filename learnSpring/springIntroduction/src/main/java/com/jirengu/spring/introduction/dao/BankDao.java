package com.jirengu.spring.introduction.dao;

import com.jirengu.spring.introduction.mapper.AccountPOMapper;
import com.jirengu.spring.introduction.pojo.AccountPO;
import com.jirengu.spring.introduction.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class BankDao implements IBankDao {

    public AccountPO selectById(int id) {
        SqlSession session = SqlSessionUtil.getSqlSession();
        AccountPOMapper mapper = session.getMapper(AccountPOMapper.class);
        return mapper.selectByPrimaryKey(id);
    }

    public int updateAccount(AccountPO accountPO) {
        SqlSession session = SqlSessionUtil.getSqlSession();
        AccountPOMapper mapper = session.getMapper(AccountPOMapper.class);
        int count = mapper.updateByPrimaryKey(accountPO);
        session.commit();
        session.close();
        return count;
    }

}

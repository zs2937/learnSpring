package com.jirengu.spring.mvc.service;

import com.jirengu.spring.mvc.excpetion.BusinessException;
import com.jirengu.spring.mvc.excpetion.ParamException;
import com.jirengu.spring.mvc.mapper.AccountPOMapper;
import com.jirengu.spring.mvc.pojo.AccountPO;
import com.jirengu.spring.mvc.request.TransferMoneyRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements IAccountService {
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
    public AccountPO queryAccount(int id) {
        return accountPOMapper.selectByPrimaryKey(id);
    }

    private AccountPO queryExistAccount(int id) throws BusinessException {
        AccountPO accountPO = accountPOMapper.selectByPrimaryKey(id);
        if (accountPO == null) {
            throw new BusinessException(String.format("账户 id = %d 不存在", id));
        }
        return accountPO;
    }

    private void paramCheck(TransferMoneyRequest request) throws ParamException {
        if (request == null) {
            throw new ParamException("request不能为空.");
        }
        Integer fromAccountId = request.getFromAccountId();
        Integer toAccountId = request.getToAccountInd();
        Integer money = request.getMoney();
        if (fromAccountId == null) {
            throw new ParamException("转出账户不能为空");
        }
        if (toAccountId == null) {
            throw new ParamException("转入账户不能为空");
        }
        if (money == null) {
            throw new ParamException("转账金额不能为空");
        }
        if (fromAccountId <= 0) {
            throw new ParamException("转出账户id必须大于0");
        }
        if (toAccountId <= 0) {
            throw new ParamException("转入账户id必须大于0");
        }
        if (money <= 0) {
            throw new ParamException("转账金额必须大于0");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transferMoney(TransferMoneyRequest request) throws ParamException {
        // 参数检查
        paramCheck(request);
        int fromAccountId = request.getFromAccountId();
        int toAccountId = request.getToAccountInd();
        int money = request.getMoney();
        // 获取转出账户
        AccountPO fromAccount = queryExistAccount(fromAccountId);
        // 获取转入账户
        AccountPO toAccount = queryExistAccount(toAccountId);
        // 检查转账金额大于转出账户余额
        if (money > fromAccount.getMoney()) {
            // 转账金额大于余额
            throw new BusinessException("余额不足，转账失败");
        }
        // 扣减转出账户余额
        fromAccount.setMoney(fromAccount.getMoney() - money);
        int fromCount = accountPOMapper.updateByPrimaryKeySelective(fromAccount);
        // 增加转入账户余额
        toAccount.setMoney(toAccount.getMoney() + money);
        int toCount = accountPOMapper.updateByPrimaryKeySelective(toAccount);
        if (fromCount + toCount != 2) {
            throw new RuntimeException("转账数据异常");
        }
    }
}

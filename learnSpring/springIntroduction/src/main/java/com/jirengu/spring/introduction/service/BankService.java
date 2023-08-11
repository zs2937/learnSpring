package com.jirengu.spring.introduction.service;

import com.jirengu.spring.introduction.dao.IBankDao;
import com.jirengu.spring.introduction.pojo.AccountPO;
import com.jirengu.spring.introduction.pojo.BankOperationResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BankService implements IBankService {

    @Resource
    private IBankDao bankDao;

    @Resource
    private IUserService userService;

    public BankOperationResult balanceInquiry(int id) {
        BankOperationResult saveOrWithdrawMoneyResult = new BankOperationResult();
        AccountPO account = bankDao.selectById(id);
        if (account == null) {
            saveOrWithdrawMoneyResult.setResult(false);
            String message = String.format("account %d not exists.", id);
            saveOrWithdrawMoneyResult.setFailReason(message);
            return saveOrWithdrawMoneyResult;
        }
        saveOrWithdrawMoneyResult.setResult(true);
        saveOrWithdrawMoneyResult.setBalance(account.getMoney());
        return saveOrWithdrawMoneyResult;
    }

    public BankOperationResult saveMoney(int id, int money) {
        BankOperationResult saveOrWithdrawMoneyResult = new BankOperationResult();
        AccountPO account = bankDao.selectById(id);
        if (account == null) {
            saveOrWithdrawMoneyResult.setResult(false);
            String message = String.format("account %d not exists.", id);
            saveOrWithdrawMoneyResult.setFailReason(message);
            return saveOrWithdrawMoneyResult;
        }
        int balance = account.getMoney();
        account.setMoney(balance + money);
        int updateCount = bankDao.updateAccount(account);
        if (updateCount > 0) {
            // 更新成功
            saveOrWithdrawMoneyResult.setResult(true);
            saveOrWithdrawMoneyResult.setBalance(account.getMoney());
        } else {
            // 更新失败
            saveOrWithdrawMoneyResult.setResult(false);
            saveOrWithdrawMoneyResult.setFailReason("save fail");
        }
        return saveOrWithdrawMoneyResult;
    }

    public BankOperationResult withdrawMoney(int id, int money) {
        BankOperationResult saveOrWithdrawMoneyResult = new BankOperationResult();
        AccountPO account = bankDao.selectById(id);
        if (account == null) {
            saveOrWithdrawMoneyResult.setResult(false);
            String message = String.format("account %d not exists.", id);
            saveOrWithdrawMoneyResult.setFailReason(message);
            return saveOrWithdrawMoneyResult;
        }
        int balance = account.getMoney();
        // 检查要取的金额是否大于余额
        if (money > balance) {
            saveOrWithdrawMoneyResult.setResult(false);
            String message = String.format("balance %d < withdraw money %d", balance, money);
            saveOrWithdrawMoneyResult.setFailReason(message);
            return saveOrWithdrawMoneyResult;
        }
        account.setMoney(balance - money);
        int updateCount = bankDao.updateAccount(account);
        if (updateCount > 0) {
            // 更新成功
            saveOrWithdrawMoneyResult.setResult(true);
            saveOrWithdrawMoneyResult.setBalance(account.getMoney());
        } else {
            // 更新失败
            saveOrWithdrawMoneyResult.setResult(false);
            saveOrWithdrawMoneyResult.setFailReason("withdraw fail");
        }
        return saveOrWithdrawMoneyResult;
    }

}

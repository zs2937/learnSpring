package com.jirengu.spring.mvc.request;


public class TransferMoneyRequest {

    private Integer fromAccountId;

    private Integer toAccountInd;

    private Integer money;

    public Integer getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Integer fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Integer getToAccountInd() {
        return toAccountInd;
    }

    public void setToAccountInd(Integer toAccountInd) {
        this.toAccountInd = toAccountInd;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}

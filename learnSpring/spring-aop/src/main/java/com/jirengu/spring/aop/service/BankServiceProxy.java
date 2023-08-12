package com.jirengu.spring.aop.service;

public class BankServiceProxy {
    private BankService bankService;
    public BankServiceProxy(BankService bankService) {
        this.bankService = bankService;
    }

    public int inquiryBalance(int id) {
        long startTime = System.currentTimeMillis();
        int result = bankService.inquiryBalance(id);
        long endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println("inquiryBalance 执行完成，耗时 " + interval + "毫秒");
        return result;
    }

    public void saveMoney(int id, int money) {
        bankService.saveMoney(id, money);
    }

    public void withdrawMoney(int id, int money) {
        bankService.withdrawMoney(id, money);
    }

}

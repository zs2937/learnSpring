package com.jirengu.spring.aop.service;


public class BankService {

    public int inquiryBalance(int id) {
        System.out.println("inquiryBalance 核心流程");
        return 1000;
    }

    public void saveMoney(int id, int money) {
        System.out.println("saveMoney 核心流程");
    }

    public void withdrawMoney(int id, int money) {

        System.out.println("withdrawMoney 核心流程");

    }



}

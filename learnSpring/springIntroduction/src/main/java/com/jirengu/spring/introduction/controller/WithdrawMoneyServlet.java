package com.jirengu.spring.introduction.controller;

import com.jirengu.spring.introduction.context.MyApplicationContext;
import com.jirengu.spring.introduction.pojo.BankOperationResult;
import com.jirengu.spring.introduction.service.IBankService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "withdrawMoneyServlet", urlPatterns = "/withdrawMoney")
public class WithdrawMoneyServlet extends HttpServlet {

    private IBankService bankService = (IBankService) MyApplicationContext.getBean("bankService");

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        System.out.println(bankService);
        int id = Integer.parseInt(req.getParameter("id"));
        int money = Integer.parseInt(req.getParameter("money"));
        BankOperationResult bankOperationResult = bankService.withdrawMoney(id, money);
        String message;
        if (bankOperationResult.isResult()) {
            message = String.format("withdraw success, balance of account %d is %d yuan", id, bankOperationResult.getBalance());
        } else {
            message = String.format("withdraw fail, fail reason: %s", bankOperationResult.getFailReason());
        }
        resp.getWriter().write(message);
    }
}

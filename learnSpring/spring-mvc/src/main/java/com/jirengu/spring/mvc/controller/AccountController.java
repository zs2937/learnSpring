package com.jirengu.spring.mvc.controller;

import com.jirengu.spring.mvc.Response;
import com.jirengu.spring.mvc.constant.ResponseCode;
import com.jirengu.spring.mvc.excpetion.BusinessException;
import com.jirengu.spring.mvc.excpetion.ParamException;
import com.jirengu.spring.mvc.pojo.AccountPO;
import com.jirengu.spring.mvc.request.TransferMoneyRequest;
import com.jirengu.spring.mvc.service.IAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    private IAccountService accountService;
    @RequestMapping(value = "/balanceInquiry", method = RequestMethod.GET)
    public String balanceInquiry(@RequestParam("id") Integer id) {
        return accountService.balanceInquiry(id).toString();
    }

    @RequestMapping(value = "/queryAccount", method = RequestMethod.GET)
    public AccountPO queryAccount(@RequestParam("id") Integer id) {
        return accountService.queryAccount(id);
    }

    @GetMapping("/queryAccountV2")
    public Response<AccountPO> queryAccountV2(@RequestParam("id") Integer id) {
        System.out.println("queryAccountV2 执行");
        Response<AccountPO> response = new Response<>();
        AccountPO account =  accountService.queryAccount(id);
        if (account == null) {
            response.setCode(ResponseCode.PARAM_ERROR);
            response.setMessage("account not exist.");
        } else {
            response.setCode(ResponseCode.SUCCESS);
            response.setData(account);
        }
        return response;
    }

    @PostMapping("/transferMoney")
    public Response<Void> transferMoney(@RequestBody TransferMoneyRequest request) {
        Response<Void> response = new Response<>();
        try {
            accountService.transferMoney(request);
        } catch (ParamException pe) {
            // 捕获参数异常
            response.setCode(ResponseCode.PARAM_ERROR);
            response.setMessage(pe.getMessage());
            return response;
        } catch (BusinessException be) {
            // 捕获业务异常
            response.setCode(ResponseCode.BUSINESS_ERROR);
            response.setMessage(be.getMessage());
            return response;
        } catch (Exception e) {
            response.setCode(ResponseCode.UNKNOWN_ERROR);
            response.setMessage("系统异常");
            return response;
        }
        response.setCode(ResponseCode.SUCCESS);
        response.setMessage("转账成功");
        return response;
    }

}

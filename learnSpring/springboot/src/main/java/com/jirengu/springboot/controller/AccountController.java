package com.jirengu.springboot.controller;

import com.jirengu.springboot.constant.ResponseCode;
import com.jirengu.springboot.excpetion.BusinessException;
import com.jirengu.springboot.excpetion.ParamException;
import com.jirengu.springboot.pojo.AccountPO;
import com.jirengu.springboot.pojo.Response;
import com.jirengu.springboot.request.TransferMoneyRequest;
import com.jirengu.springboot.service.IAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    private IAccountService accountService;

    @GetMapping("/queryAccountById")
    public Response<AccountPO> queryAccountById(@RequestParam("id") Integer id) {
        Response<AccountPO> response = new Response<>();
        AccountPO account =  accountService.queryAccountById(id);
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

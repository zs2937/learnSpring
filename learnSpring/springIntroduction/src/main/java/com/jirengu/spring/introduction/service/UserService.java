package com.jirengu.spring.introduction.service;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements IUserService {

    @Resource
    private IBankService bankService;

}

package com.fizzyi.service.impl;

import com.fizzyi.mapper.LoginMapper;
import com.fizzyi.pojo.Emp;
import com.fizzyi.pojo.LoginParams;
import com.fizzyi.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/8/11
 */

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    public Emp login(LoginParams loginParams) {
        Emp emp = loginMapper.login(loginParams);
        return emp;
    }
}

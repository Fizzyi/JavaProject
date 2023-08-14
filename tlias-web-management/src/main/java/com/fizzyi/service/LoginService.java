package com.fizzyi.service;

import com.fizzyi.pojo.Emp;
import com.fizzyi.pojo.LoginParams;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/8/11
 */
public interface LoginService {

    /**
     * 登录操作
     * @param loginParams
     * @return 0 失败 1 成功
     */
    Emp login(LoginParams loginParams);
}

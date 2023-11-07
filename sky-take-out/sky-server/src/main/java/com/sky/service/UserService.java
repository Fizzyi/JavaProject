package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/7
 */
public interface UserService {

    // wx登录
    User wxlogin(UserLoginDTO userLoginDTO);
}

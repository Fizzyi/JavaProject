package com.fizzyi.controller;

import com.fizzyi.pojo.Emp;
import com.fizzyi.pojo.LoginParams;
import com.fizzyi.pojo.Result;
import com.fizzyi.service.LoginService;
import com.fizzyi.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/8/11
 */
@RestController
@Slf4j
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * 登录
     *
     * @param loginParams 登录参数
     * @return result
     */
    @PostMapping
    public Result login(@RequestBody LoginParams loginParams) {
        log.info("进行登录操作：{}", loginParams);
        Emp emp = loginService.login(loginParams);
        if (emp != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", emp.getId());
            claims.put("username", emp.getUsername());
            claims.put("name", emp.getName());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        } else {
            return Result.error("登录失败");
        }
    }
}

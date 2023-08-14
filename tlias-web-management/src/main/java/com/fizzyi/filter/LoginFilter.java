package com.fizzyi.filter;

import com.alibaba.fastjson.JSONObject;
import com.fizzyi.pojo.Result;
import com.fizzyi.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/8/11
 */

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        // 1.获取请求 url
        String url = req.getRequestURI();
        log.info("获得 url:{}",url);
        // 2.判断请求 url 中是否包含 login，如果包含，则说明是登录操作，放行
        if (url.contains("login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 3.获取请求头中的令牌 token
        String jwt = req.getHeader("token");
        log.info("获得 token：{}",jwt);
        // 4.判断令牌是否存在，如果不存在，返回错误结果
        if (!StringUtils.hasLength(jwt)) {
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象 -- 》 json
            String noLogin = JSONObject.toJSONString(error);
            // 将数据写入 resp
            resp.getWriter().write(noLogin);
            return;
        }
        // 5.解析 token，如果解析失败，返回错误结果
        try {
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象 -- 》 json
            String noLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(noLogin);
            return;
        }
        // 6 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

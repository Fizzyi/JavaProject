package com.fizzyi.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fizzyi.pojo.Result;
import com.fizzyi.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/8/14
 */

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override //在目标资源运行前运行，返回 true，放行，返回 false，不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求 url
        String url = request.getRequestURI();
        log.info("获得 url:{}",url);
        // 2.判断请求 url 中是否包含 login，如果包含，则说明是登录操作，放行

        // 3.获取请求头中的令牌 token
        String jwt = request.getHeader("token");
        log.info("获得 token：{}",jwt);
        // 4.判断令牌是否存在，如果不存在，返回错误结果
        if (!StringUtils.hasLength(jwt)) {
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象 -- 》 json
            String noLogin = JSONObject.toJSONString(error);
            // 将数据写入 resp
            response.getWriter().write(noLogin);
            return false;
        }
        // 5.解析 token，如果解析失败，返回错误结果
        try {
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象 -- 》 json
            String noLogin = JSONObject.toJSONString(error);
            response.getWriter().write(noLogin);
            return false;
        }
        System.out.println("preHandle....");
        return  true;
    }

    @Override // 目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle....");
    }

    @Override // 视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion....");
    }
}

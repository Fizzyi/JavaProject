package com.fizzyi.aop;

import com.alibaba.fastjson.JSONObject;
import com.fizzyi.mapper.LoginMapper;
import com.fizzyi.mapper.OperateLogMapper;
import com.fizzyi.pojo.OperateLog;
import com.fizzyi.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/8/17
 */

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.fizzyi.anno.Log)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        OperateLog operateLog = new OperateLog();
        // 操作人ID

        // 获取请求头中jwt令牌，解析令牌
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        operateLog.setId((Integer) claims.get("id"));
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName()); //获取类名
        operateLog.setMethodName(joinPoint.getSignature().getName()); // 获取方法名
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs())); //获取参数
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed(); //调用原始目标方法执行，获取结果
        operateLog.setReturnValue(JSONObject.toJSONString(result)); //设置返回值
        long end = System.currentTimeMillis();
        operateLog.setCostTime(end - begin);
        operateLogMapper.insert(operateLog);
        return result;
    }
}

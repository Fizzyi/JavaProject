package com.fizzyi.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/8/16
 */

@Component
@Slf4j
@Aspect // AOP类 切面类
public class TimeAspect {

    @Around("execution(* com.fizzyi.service.*.*(..))") // 切入点表达式
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //1 记录开始时间
        long begin = System.currentTimeMillis();

        //2 调用原始方法执行
        Object result = joinPoint.proceed();

        //3 记录结束时间，计算方法执行耗时
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + "方法耗时：{}ms", end - begin);
        return result;
    }
}

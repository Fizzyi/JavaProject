package com.fizzyi.exception;

import com.fizzyi.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author Zhaohangyi
 * @time 2023/8/14
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // Exception.class 代表捕获所有异常
    public Result ex(Exception ex) {
        ex.printStackTrace(); // 打印堆栈信息
        return Result.error("操作失败，联系管理员");
    }
}

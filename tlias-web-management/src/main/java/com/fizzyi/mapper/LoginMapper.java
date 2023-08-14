package com.fizzyi.mapper;

import com.fizzyi.pojo.Emp;
import com.fizzyi.pojo.LoginParams;
import org.apache.ibatis.annotations.Mapper;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/8/11
 */

@Mapper
public interface LoginMapper {

    public Emp login(LoginParams loginParams);
}

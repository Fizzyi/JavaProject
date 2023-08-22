package com.fizzyi.mapper;

import com.fizzyi.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/8/17
 */
@Mapper
public interface OperateLogMapper {


    @Insert("insert into operate_log (operate_user, operate_time, class_name, method_name, method_params, return_value, cost_time)" +
            " values (#{operateUser},#{operateTime},#{className},#{methodName},#{methodParams},#{returnValue},#{costTime} )")
    public void insert(OperateLog log);
}

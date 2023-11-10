package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/10
 */
@Mapper
public interface OrderDetailMapper {
    void insertMany(List<OrderDetail> orderDetails);
}

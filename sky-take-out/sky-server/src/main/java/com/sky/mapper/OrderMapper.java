package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/10
 */
@Mapper
public interface OrderMapper {
    Long insert(Orders orders);

    Orders getByNumber(String outTradeNo);

    void update(Orders orders);
}

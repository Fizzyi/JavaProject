package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/9
 */


@Mapper
public interface ShoppingCartMapper {

    /**
     * 动态查询接口
     *
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     * 通过id 更新数据
     *
     * @param cart
     */
    void update(ShoppingCart cart);

    /**
     * 新增数据
     *
     * @param shoppingCart
     */
    void insert(ShoppingCart shoppingCart);
}

package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/9
 */
public interface ShoppingCartService {

    void add(ShoppingCartDTO shoppingCartDTO);

    List<ShoppingCart> list();

    void clean();

    void sub(ShoppingCartDTO shoppingCartDTO);
}

package com.sky.service;

import com.sky.dto.DishDTO;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/9/5
 */
public interface DishService {

    /**
     * 新增菜品和口味
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);
}

package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/9/5
 */
@Service
public interface DishService {

    /**
     * 新增菜品和口味
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 通过 ID 批量删除菜品
     * @param ids
     * @return
     */
    public Result deleteDish(List<Integer> ids);
}

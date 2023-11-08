package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.vo.DishVO;
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
     *
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * 菜品分页查询
     *
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 通过 ID 批量删除菜品
     *
     * @param ids
     * @return
     */
    Result deleteDish(List<Long> ids);

    /**
     * 通过 ID 查询菜品
     *
     * @param id
     * @return
     */
    DishVO getById(Long id);

    /**
     * 通过ID更新菜品信息
     *
     * @param dishDTO
     */
    void updateById(DishDTO dishDTO);

    /**
     * 通过 category_id 查询菜品
     *
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);

    /**
     * 通过 dishId 修改 是否停售
     *
     * @param dish
     */
    void updateDishStatus(Dish dish);

    List<Dish> list(Dish dish);
}

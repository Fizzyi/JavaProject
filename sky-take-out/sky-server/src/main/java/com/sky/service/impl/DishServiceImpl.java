package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.SetmealDish;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/9/5
 */

@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    /**
     * 新增菜品和口味
     *
     * @param dishDTO
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO) {

        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        // 向菜品表插入1条数据
        dishMapper.insert(dish);
        Long dishId = dish.getId();
        List<DishFlavor> flavorList = dishDTO.getFlavors();
        if (flavorList != null && flavorList.size() > 0) {
            flavorList.forEach(dishFlavor -> dishFlavor.setDishId(dishId));
            // 向口味表插入多条数据
            dishFlavorMapper.insertBatch(flavorList);
        }
    }

    /**
     * 菜品分页查询
     *
     * @param dishPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 通过ID批量删除菜品
     *
     * @param idList
     * @return
     */
    @Transactional
    @Override
    public Result deleteDish(List<Integer> idList) {
        // 通过ID批量查询菜品数据
        List<Dish> dishList = dishMapper.select(idList);
        log.info("通过ID批量查询菜品信息:{}", dishList);
        for (Dish dish : dishList) {
            // 遍历判断是否有菜品有在起售状态
            if (dish.getStatus().equals(StatusConstant.ENABLE)) {
                log.info(MessageConstant.DISH_ON_SALE);
                return Result.error(MessageConstant.DISH_ON_SALE);
            }
        }
        // 查询菜品是否被套餐关联
        for (Object id : idList) {
            SetmealDish setmealDish = dishMapper.findRelationSetmealByDishId((Integer) id);
            if (setmealDish != null) {
                return Result.error(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
            }
        }
        // 删除菜品数据
        dishMapper.deleteByIds(idList);
        // 删除口味数据
        dishFlavorMapper.deleteByDishId(idList);
        return Result.success();
    }
}

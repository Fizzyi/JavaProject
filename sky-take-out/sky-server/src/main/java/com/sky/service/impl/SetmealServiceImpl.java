package com.sky.service.impl;

import com.sky.entity.Setmeal;
import com.sky.mapper.SetmealMapper;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/7
 */

@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 根据分类id查询套餐
     *
     * @param setmeal
     * @return
     */
    @Override
    public List<SetmealVO> list(Setmeal setmeal) {
        List<SetmealVO> setmealVOList = setmealMapper.list(setmeal);
        log.info("根据分类地查询套餐的返回结果为:{}",setmealVOList);
        return setmealVOList;
    }

    /**
     * 根据套餐id查询包含的菜品
     * @param id
     * @return
     */
    @Override
    public List<DishItemVO> getDishItemById(Long id) {
        return setmealMapper.getDishItemById(id);
    }
}

package com.sky.service;

import com.sky.entity.Setmeal;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/7
 */


public interface SetmealService {
    List<SetmealVO> list(Setmeal setmeal);

    List<DishItemVO> getDishItemById(Long id);
}

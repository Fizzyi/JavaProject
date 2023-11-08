package com.sky.controller.user;

import com.sky.constant.StatusConstant;
import com.sky.entity.Dish;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/7
 */

@RestController("userDishController")
@RequestMapping("/user/dish")
@Api(tags = "C端菜品")
public class DishController {


    @Autowired
    private DishService dishService;

    /**
     * 根据分类 id 查询菜品
     *
     * @param categoryId 分类id
     * @return list<dish>
     */
    @GetMapping("/list")
    public Result<List<DishVO>> list(Long categoryId) {
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);
        List<DishVO> dishList = dishService.listWithFlavor(dish);
        return Result.success(dishList);
    }
}

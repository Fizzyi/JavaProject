package com.sky.controller.user;

import com.sky.entity.Setmeal;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/7
 */

@RestController
@RequestMapping("/user/setmeal")
@Api(tags = "C端套餐")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据分类id 查询套餐
     *
     * @param categoryId 分类地
     * @return SetmealVO
     */
    @GetMapping("/list")
    public Result<List<SetmealVO>> list(Long categoryId) {
        log.info("根据分类id 查询套餐,接受的参数为:{}",categoryId);
        Setmeal setmeal = new Setmeal();
        setmeal.setCategoryId(categoryId);
        List<SetmealVO> setmealVOList = setmealService.list(setmeal);
        return Result.success(setmealVOList);
    }

    /**
     * 根据套餐id查询包含的菜品
     *
     * @param id 套餐id
     * @return
     */
    @GetMapping("/dish/{id}")
    public Result<List<DishItemVO>> dishList(@PathVariable Long id) {
        List<DishItemVO> dishItemVOList = setmealService.getDishItemById(id);
        return Result.success(dishItemVOList);
    }
}

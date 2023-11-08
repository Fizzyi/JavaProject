package com.sky.controller.user;

import com.sky.constant.StatusConstant;
import com.sky.entity.Dish;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
@Slf4j
public class DishController {


    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据分类 id 查询菜品
     *
     * @param categoryId 分类id
     * @return list<dish>
     */
    @GetMapping("/list")
    @ApiOperation("根据分类地查询菜品")
    public Result<List<DishVO>> list(Long categoryId) {
        // 构造 redis 中的 key
        String key = "dish_" + categoryId;
        // 查询 redis 中是否存在菜品数据 放进去的是什么类型，取出来的就是什么类型
        List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get(key);
        if (list != null && !list.isEmpty()) {
            // 如果存在，直接返回，无须查询数据库
            log.info("redis中有对应数据，无需查找数据库");
        } else {
            // 如果不存在，查询数据库，将查询到的数据存入到 redis 中
            log.info("redis中没有数据，进行数据库查询");
            Dish dish = new Dish();
            dish.setCategoryId(categoryId);
            dish.setStatus(StatusConstant.ENABLE);
            list = dishService.listWithFlavor(dish);
            redisTemplate.opsForValue().set(key, list);
        }
        return Result.success(list);
    }
}

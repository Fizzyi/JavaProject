package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜品管理
 *
 * @author Zhaohangyi
 * @time 2023/9/5
 */

@RestController("adminDishController")
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping
    @ApiOperation(value = "新增菜品")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品:{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        // 需要删除 redis 中该菜品所属分类的缓存。 调用的通用方法
        cleanCache("dish_" + dishDTO.getCategoryId());
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation(value = "菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询:{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping()
    @ApiOperation(value = "批量删除菜品")
    public Result deleteDish(@RequestParam List<Long> ids) {
        log.info("批量删除菜品:{}", ids);
        // 将所有的菜品缓存都清理，所有以 dish_开头的 key。需要先查出所有的 key，在进行批量删除
        cleanCache("dish_*");
        return dishService.deleteDish(ids);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID查询菜品")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("通过ID查询菜品，查询的ID为：{}", id);
        DishVO dishVO = dishService.getById(id);
        return Result.success(dishVO);
    }

    @PutMapping()
    @ApiOperation(value = "修改菜品")
    public Result updateDish(@RequestBody DishDTO dishDTO) {
        log.info("通过ID修改菜品，参数为：{}", dishDTO);
        dishService.updateById(dishDTO);
        // 更新 redis 缓存，正常的将应该比较下 修改是否修改了分类id 如果没有修改则不需要更新 redis 缓存，这里简单处理就删除所有的缓存其实不太好。
        cleanCache("dish_*");
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation(value = "修改菜品起售状态")
    public Result updateDishStatus(@PathVariable Integer status, @RequestParam("id") Long dishId) {
        log.info("修改菜品是否起售,参数为:{},{}", status, dishId);
        Dish dish = new Dish();
        dish.setId(dishId);
        dish.setStatus(status);
        dishService.updateDishStatus(dish);
        return Result.success();
    }

    /**
     * 根据分类id 查询菜品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "根据分类id 查询菜品")
    public Result<List<Dish>> list(@RequestParam Long categoryId) {
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        List<Dish> list = dishService.list(dish);
        return Result.success(list);
    }

    /**
     * 清理缓存
     *
     * @param pattern
     */
    private void cleanCache(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}

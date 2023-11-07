package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PostMapping
    @ApiOperation(value = "新增菜品")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品:{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
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
        return Result.success();
    }

}

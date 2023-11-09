package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

/**
 * 套餐相关
 *
 * @author Zhaohangyi
 * @time 2023/11/8
 */

@RestController("userSetmealController")
@RequestMapping("/admin/setmeal")
@Api(tags = "套餐相关接口")
@Slf4j
public class SetmealController {

    @Autowired
    public SetmealService setmealService;

    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("")
    @ApiOperation("新增套餐")
    @CacheEvict(cacheNames = "setmeal",key="#setmealDTO.categoryId")
    public Result save(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐接受的参数：{}", setmealDTO);
        setmealService.save(setmealDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id 查询套餐")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        SetmealDTO setmealDTO = new SetmealDTO();
        setmealDTO.setId(id);
        SetmealVO setmealVO = setmealService.getById(setmealDTO);
        return Result.success(setmealVO);
    }
    // todo:删除套餐接口
    // todo:更新套餐接口
}

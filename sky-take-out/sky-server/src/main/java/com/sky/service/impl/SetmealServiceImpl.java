package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.DishVO;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
        log.info("根据分类地查询套餐的返回结果为:{}", setmealVOList);
        return setmealVOList;
    }

    /**
     * 根据套餐id查询包含的菜品
     *
     * @param id
     * @return
     */
    @Override
    public List<DishItemVO> getDishItemById(Long id) {
        return setmealMapper.getDishItemById(id);
    }

    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> page = setmealMapper.pageQuery(setmealPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 新增套餐
     *
     * @param setmealDTO
     */
    @Override
    public void save(SetmealDTO setmealDTO) {
        // 新增套餐 得到套餐id
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.save(setmeal);
        Long setmealId = setmeal.getId();
        List<SetmealDish> setmealDishList = setmealDTO.getSetmealDishes();
        if (setmealDishList != null & !setmealDishList.isEmpty()) {
            setmealDishList.forEach(setmealDish -> setmealDish.setSetmealId(setmealId));
            // 向套餐菜品关联表插入多条数据
            setmealMapper.insetSetmealDishList(setmealDishList);
        }
        log.info("套餐的内容:{},{}", setmeal);
    }

    /**
     * 通过id 查询套餐
     *
     * @param setmealDTO
     * @return setmealVO
     */
    @Override
    public SetmealVO getById(SetmealDTO setmealDTO) {
        SetmealVO setmealVO = setmealMapper.getById(setmealDTO);
        List<SetmealDish> setmealDishList = setmealMapper.getSetmealDishById(setmealDTO);
        setmealVO.setSetmealDishes(setmealDishList);
        return setmealVO;
    }
}

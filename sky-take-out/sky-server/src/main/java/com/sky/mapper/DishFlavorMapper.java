package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/9/5
 */
@Mapper
public interface DishFlavorMapper {

    void insertBatch(List<DishFlavor> flavorList);
}

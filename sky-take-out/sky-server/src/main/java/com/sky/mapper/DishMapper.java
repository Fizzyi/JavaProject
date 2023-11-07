package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     *
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 插入菜品数据
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 通过 ID批量查询菜品信息
     * @param idList
     * @return
     */
    List<Dish> select(List idList);

    /**
     * 通过dishID查询 菜品和菜单的关联关系
     * @param id
     * @return
     */
    SetmealDish findRelationSetmealByDishId(Integer id);

    /**
     * 通过ID删除菜品数据
     * @param idList
     */
    void deleteByIds(List<Long> idList);

    /**
     * 通过ID查询菜品
     * @param id
     * @return
     */
    Dish getById(Long id);

    /**
     * 通过ID更新数据
     * @param dish
     */
    void updateById(Dish dish);

    /**
     * 通过 categoryId 查询菜品列表
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);
}

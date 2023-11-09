package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.SetmealDTO;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/9
 */
@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 添加购物车
     *
     * @param shoppingCartDTO
     */
    @Override
    public void add(ShoppingCartDTO shoppingCartDTO) {
        // 判断商品是否存在
        // 组装参数
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        Long id = BaseContext.getCurrentId();
        shoppingCart.setUserId(id);
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.list(shoppingCart);
        if (!shoppingCarts.isEmpty()) {
            // 如果存在，则数量+1,进行更新数据
            ShoppingCart cart = shoppingCarts.get(0);
            cart.setNumber(cart.getNumber() + 1);
            shoppingCartMapper.update(cart);
        } else {
            // 如果不存在，则新增一条

            if (shoppingCartDTO.getDishId() != null) {
                //本次添加到购物车的是菜品
                Dish dish = dishMapper.getById(shoppingCartDTO.getDishId());
                shoppingCart.setAmount(dish.getPrice());
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
            } else {
                // 套餐
                SetmealDTO setmealDTO = new SetmealDTO();
                setmealDTO.setId(shoppingCartDTO.getSetmealId());
                SetmealVO setmealVO = setmealMapper.getById(setmealDTO);
                shoppingCart.setAmount(setmealVO.getPrice());
                shoppingCart.setName(setmealVO.getName());
                shoppingCart.setImage(setmealVO.getImage());
            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartMapper.insert(shoppingCart);
        }


    }

    /**
     * 查看用户购物车列表
     *
     * @return
     */
    @Override
    public List<ShoppingCart> list() {
        // 获取用户id
        Long id = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(id);
        return shoppingCartMapper.list(shoppingCart);
    }

    /**
     * 清空购物车
     */
    @Override
    public void clean() {
        Long id = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(id);
        shoppingCartMapper.cleanShoppingCert(shoppingCart);
    }

    /**
     * 删除购物车中的一个商品
     * @param shoppingCartDTO
     */
    @Override
    public void sub(ShoppingCartDTO shoppingCartDTO) {
        Long id = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(id);
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        shoppingCartMapper.cleanShoppingCert(shoppingCart);

    }
}

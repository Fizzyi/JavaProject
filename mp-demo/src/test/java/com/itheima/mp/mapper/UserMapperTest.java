package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        User user = new User();
        user.setId(5L);
        user.setUsername("Lucy");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    void testSelectById() {
        User user = userMapper.selectById(5L);
        System.out.println("user = " + user);
    }


    @Test
    void testQueryByIds() {
        List<User> users = (List<User>) userMapper.selectById((Serializable) List.of(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setBalance(20000);
        userMapper.updateById(user);
    }

    @Test
    void testDeleteUser() {
        userMapper.deleteById(5L);
    }

    // QueryWrapper
    @Test
    void testQueryWrapper() {
        // 1. 构建查询条件 where username like '%o%' and balance > 1000
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .select("id", "username", "info", "balance")
                .like("username", "o")
                .ge("balance", 1000);
        // 2.查询数据
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    void testUpdateByQueryWrapper() {
        // 构建查询条件 where name = "Jack"
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("username", "Jack");
        // 更新数据 user 中的非 null 字段都会作为 set 语句。
        User user = new User();
        user.setBalance(2000);
        userMapper.update(user, wrapper);
    }

    // UpdateWrapper 基于 BaseMapper 中的 update 方法更新时只能直接赋值，对于一些复杂的需求就难以实现。
    @Test
    void testUpdateWrapper() {
        List<Long> ids = List.of(1L, 2L, 4L);
        // 1.生成 sql
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>()
                .setSql("balance = balance - 200") // SET balance = balance - 200
                .in("id", ids); // WHERE id in (1,2,4)
        // 2.更新 第一个参数可以给 null，也就是不填更新字段和数据
        userMapper.update(null, wrapper);

    }

    // LambdaQueryWrapper

    @Test
    void testLambdaQueryWrapper(){
        // 1.构建条件，WHERE username LIKE "&o&" AND balance >= 1000
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .select(User::getId,User::getUsername,User::getInfo,User::getBalance)
                .like(User::getUsername,"o")
                .ge(User::getBalance,"1000");
        // 查询
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);

    }




}
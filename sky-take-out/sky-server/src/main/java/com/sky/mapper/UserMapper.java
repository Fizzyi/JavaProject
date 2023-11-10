package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/7
 */
@Mapper
public interface UserMapper {

    /**
     * 根据 openid 查询用户
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    void insert(User user);

    User getById(Long userId);
}

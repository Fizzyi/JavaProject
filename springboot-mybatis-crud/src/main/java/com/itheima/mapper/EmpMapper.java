package com.itheima.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface EmpMapper {

    //根据 id 删除数据
    @Delete("delete from emp where id = #{id}")
    public void delete(Integer id);
}

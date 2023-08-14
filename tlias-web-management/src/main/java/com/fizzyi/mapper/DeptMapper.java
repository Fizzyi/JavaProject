package com.fizzyi.mapper;

import com.fizzyi.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/7/31
 */
@Mapper
public interface DeptMapper {

    /**
     * 查询全部部门数据
     * @return
     */
    @Select("select * from dept")
    List<Dept> list();


    /**
     * 通过 id 删除部门信息
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void delete(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void addDept(Dept dept);

    /**
     *  通过 id 查询部门信息
     * @param id
     * @return
     */
    @Select("select * from dept where id = #{id}")
    Dept selectById(Integer id);


    /**
     * 通过 id 更新部门信息
     * @param dept
     */
    @Update("update dept set name =#{name} where id = #{id}")
    void updateById(Dept dept);
}

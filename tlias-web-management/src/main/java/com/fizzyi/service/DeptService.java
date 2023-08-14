package com.fizzyi.service;

import com.fizzyi.pojo.Dept;

import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/7/31
 */
public interface DeptService {

    /**
     * 查询全部部门信息
     * @return list
     */
    List<Dept> list();


    /**
     * 通过 id 删除某个部门
     * @param id 部门 id
     */
    void delete(Integer id);

    /**
     * 新增部门
     * @param dept dept
     */
    void addDept(Dept dept);

    /**
     * 通过 id 查询部门信息
     * @param id 部门 id
     * @return dept
     */
    Dept selectById(Integer id);

    /**
     * 通过 id 更新部门信息
     * @param dept dept
     */
    void updateById(Dept dept);
}

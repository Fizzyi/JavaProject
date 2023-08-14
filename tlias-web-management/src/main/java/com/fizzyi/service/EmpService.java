package com.fizzyi.service;

import com.fizzyi.pojo.Emp;
import com.fizzyi.pojo.PageBean;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/7/31
 */
public interface EmpService {

    /**
     *  员工分页查询
     * @param page 页码
     * @param pageSize 每页数量
     * @param name 名称
     * @param gender 性别
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender,
                  LocalDateTime start,
                  LocalDateTime end);

    /**
     * 通过 id 批量删除员工
     * @param ids 员工id
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 新增员工
     * @param emp emp
     */
    void addEmp(Emp emp);

    /**
     * 通过 id 查询员工数据
     * @param id 员工 id
     * @return emp
     */
    Emp searchEmp(Integer id);

    /**
     * 修改员工数据
     * @param emp emp
     */
    void updateEmp(Emp emp);
}

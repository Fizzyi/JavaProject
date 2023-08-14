package com.fizzyi.service.impl;

import com.fizzyi.mapper.EmpMapper;
import com.fizzyi.pojo.Emp;
import com.fizzyi.pojo.PageBean;
import com.fizzyi.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/7/31
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


    /**
     * 员工列表查询
     *
     * @param page     页码
     * @param pageSize 每页条数
     * @return
     */
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender,
                         LocalDateTime start,
                         LocalDateTime end) {
        // 1. 设置分页参数
        PageHelper.startPage(page, pageSize);
        // 2.执行查询
        List<Emp> empList = empMapper.list(name,gender,start,end);
        Page<Emp> p = (Page<Emp>) empList;
        // 3.组装数据
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    /**
     * 通过 id 批量删除员工
     * @param ids 员工id
     */
    @Override
    public void deleteByIds(List<Integer> ids) {
        empMapper.deleteById(ids);
    }

    /**
     * 新增员工
     * @param emp
     */
    @Override
    public void addEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setPassword("123456");
        empMapper.addEmp(emp);
    }

    /**
     * 通过 id 查询员工数据
     * @param id 员工 id
     * @return
     */
    @Override
    public Emp searchEmp(Integer id) {
        Emp emp = empMapper.searchEmp(id);
        return emp;
    }

    /**
     * 修改员工数据
     * @param emp
     */
    @Override
    public void updateEmp(Emp emp) {
        empMapper.updateEmp(emp);
    }
}

package com.fizzyi.service.impl;

import com.fizzyi.mapper.DeptMapper;
import com.fizzyi.pojo.Dept;
import com.fizzyi.service.DeptService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public void delete(Integer id) {
        deptMapper.delete(id);
    }

    /**
     * 查询全部部门信息
     *
     * @return
     */
    @Override
    public List<Dept> list() {
        List<Dept> deptList = deptMapper.list();
        return deptList;
    }

    /**
     * 新增部门
     * @param dept
     */
    @Override
    public void addDept(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.addDept(dept);
    }

    /**
     * 通过 id 查询部门信息
     * @param id 部门 id
     * @return dept
     */
    @Override
    public Dept selectById(Integer id) {
        return deptMapper.selectById(id);
    }


    /**
     * 通过 id 更新部门信息
     * @param dept
     */
    @Override
    public void updateById(Dept dept) {
        deptMapper.updateById(dept);
    }
}

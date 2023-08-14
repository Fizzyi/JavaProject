package com.fizzyi.controller;

import com.fizzyi.pojo.Dept;
import com.fizzyi.pojo.Result;
import com.fizzyi.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理 Controller
 * Slf4j 日志记录的注解，添加后，在代码中可以直接使用 log.info 来记录日志
 *
 * @author Zhaohangyi
 * @time 2023/7/31
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * method 限制请求的方法 （1） 添加参数 method (2) 使用 GetMapping() 替换 RequestMapping()
     *
     * @return Result
     */
    @GetMapping
    public Result list() {
        log.info("查询全部部门信息");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    /**
     * 通过 id 删除部门数据
     * @param id 部门 id
     * @return result
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        log.info("通过 id：{} 删除某个部门的数据",id);
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     * @param dept dept
     * @return Result
     */
    @PostMapping
    public Result addDept(@RequestBody Dept dept){
        log.info(dept.toString());
        deptService.addDept(dept);
        return Result.success();
    }

    /**
     * 通过 id 查询部门信息
     * @param id 部门 id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        Dept dept = deptService.selectById(id);
        return Result.success(dept);
    }


    /**
     * 通过id 更新部门信息
     * @param dept 原部门信息
     * @return Result
     */
    @PutMapping
    public Result updateById(@RequestBody Dept dept){
        deptService.updateById(dept);
        return Result.success();
    }

}

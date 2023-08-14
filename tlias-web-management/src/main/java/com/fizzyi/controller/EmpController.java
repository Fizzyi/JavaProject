package com.fizzyi.controller;

import com.fizzyi.mapper.EmpMapper;
import com.fizzyi.pojo.Emp;
import com.fizzyi.pojo.PageBean;
import com.fizzyi.pojo.Result;
import com.fizzyi.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 员工管理 controller
 *
 * @author Zhaohangyi
 * @time 2023/7/31
 */
@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 员工列表查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param start
     * @param end
     * @return
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime start,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime end) {
        log.info("分页查询，参数：{},{},{},{},{},{}", page, pageSize, name, gender, start, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, start, end);
        return Result.success(pageBean);
    }

    /**
     * 批量删除员工
     *
     * @param ids 员工 id
     * @return
     */
    @DeleteMapping("/{ids}")
    public Result deleteById(@PathVariable("ids") List<Integer> ids) {
        log.info("根据 id 删除员工数据：{}", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 新增员工
     *
     * @param emp
     * @return
     */
    @PostMapping
    public Result addEmp(@RequestBody Emp emp) {
        log.info("新增员工:{}", emp);
        empService.addEmp(emp);
        return Result.success();
    }

    /**
     * 通过 id 查询员工数据
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result searchEmp(@PathVariable("id") Integer id) {
        log.info("通过 id:{} 搜索员工数据", id);
        Emp emp = empService.searchEmp(id);
        return Result.success(emp);
    }

    /**
     * 修改员工数据
     * @param emp
     * @return
     */
    @PutMapping
    public Result updateEmp(@RequestBody Emp emp){
        log.info("修改员工数据:{}",emp);
        empService.updateEmp(emp);
        return Result.success();
    }
}

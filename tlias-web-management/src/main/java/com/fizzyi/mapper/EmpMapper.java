package com.fizzyi.mapper;

import com.fizzyi.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/7/31
 */
@Mapper
public interface EmpMapper {

    /**
     * 员工条件查询
     * @param name
     * @param gender
     * @param start
     * @param end
     * @return
     */
    public List<Emp> list(String name, Short gender,
                          LocalDateTime start,
                          LocalDateTime end);

    /**
     * 通过 id 批量删除员工
     * @param ids 员工id
     */
    void deleteById(List<Integer> ids);

    /**
     * 新增员工
     * @param emp
     */
    void addEmp(Emp emp);

    /**
     * 查询员工
     * @param id
     * @return
     */
    Emp searchEmp(Integer id);

    /**
     * 修改员工数据
     * @param emp
     */
    void updateEmp(Emp emp);
}

package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO  employeeLoginDTO
     * @return Employee
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO employeeDTO
     */
    void createEmployee(EmployeeDTO employeeDTO);

    /**
     * 员工列表查询
     * @param employeePageQueryDTO employeePageQueryDTO
     * @return PageResult
     */
    PageResult list(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用，禁用员工账号
     * @param employee employee
     */
    void update(Employee employee);

    /**
     * 通过ID查询员工
     * @param id id
     * @return Employee
     */
    Employee findById(Long id);
}

package com.fizzyi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页查询结果封装类
 *
 * @author Zhaohangyi
 * @time 2023/8/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 数据列表
     */
    private List rows;
}

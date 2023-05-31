package com.xrui.service;

import com.xrui.pojo.Emp;
import com.xrui.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 11:18
 * @description: TODO
 */

public interface EmpService {
    /**
     * 分页+模糊查询
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     */
    PageBean selPage(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 根据ID删除
     * @param ids
     */
    void delById(List<Integer> ids);

    /**
     * 添加员工
     * @param emp
     */
    void addEmp(Emp emp);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 修改
     * @param emp
     */
    void updateById(Emp emp);

    /**
     * 查询
     * @param emp
     * @return
     */
    Emp selectByNamePassword(Emp emp);



}

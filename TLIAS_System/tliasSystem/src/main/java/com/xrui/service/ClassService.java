package com.xrui.service;

import com.xrui.pojo.Classes;
import com.xrui.pojo.PageBean;

import java.time.LocalDate;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 17:09
 * @description:
 */

public interface ClassService {
    /**
     * 新增班级
     * @param classes
     */
    void addClasses(Classes classes);

    /**
     * 根据ID删除班级
     * @param id
     */
    void delClaById(Integer id);

    /**
     * 根据Id查询
     * @param id
     */
    Classes selClaById(Integer id);

    /**
     * 修改方法
     * @param classes
     */
    void update(Classes classes);

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @param begin
     * @param end
     * @return
     */
    PageBean pageSel(Integer page, Integer pageSize, String name, LocalDate begin, LocalDate end);
}

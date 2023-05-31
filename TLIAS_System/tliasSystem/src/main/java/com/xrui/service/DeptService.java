package com.xrui.service;

import com.xrui.pojo.Dept;

import java.util.List;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 9:51
 * @description:
 */

public interface DeptService {

    /**
     * 查询所有部门
     * @return
     */
    List<Dept> selAllDept();

    /**
     * 根据ID删除部门
     * @param id
     */
    void delById(Integer id);

    /**
     * 添加部门
     * @param dept
     */
    void addDept(Dept dept);

    /**
     * 根据ID查询部门
     * @param id
     * @return
     */
    Dept selById(Integer id);

    /**
     * 修改员工
     * @param dept
     */
    void updateDept(Dept dept);
}

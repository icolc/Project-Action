package com.xrui.service;

import com.xrui.pojo.PageBean;
import com.xrui.pojo.Student;

/**
 * @author NXRUI
 * @date 2023/5/25 17:46
 * @description:
 * @version 1.0
 */

public interface StuService {
    /**
     * 新增员工
     * @param student
     */
    void addStu(Student student);

    /**
     * 根据ID删除学生
     * @param id
     */
    void delStuById(Integer id);

    /**
     * 根据ID查询学生
     * @param id
     */
    Student selStuById(Integer id);

    /**
     * 修改学生
     * @param student
     */
    void updateStu(Student student);

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @param highestDegree
     * @param classesId
     * @param studentNumber
     * @return
     */
    PageBean selPage(Integer page, Integer pageSize, String name, Integer highestDegree, Integer classesId, Integer studentNumber);

    /**
     * 扣分方法
     * @param student
     */
    void deductionsById(Student student);
}

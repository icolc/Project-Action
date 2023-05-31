package com.xrui.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xrui.exception.PhoneIsExistException;
import com.xrui.exception.StuNumIsExistExiception;
import com.xrui.exception.StuNumLenthExiception;
import com.xrui.mapper.StuMapper;
import com.xrui.pojo.PageBean;
import com.xrui.pojo.Student;
import com.xrui.pojo.dto.StudentDto;
import com.xrui.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 17:46
 * @description:
 */
@Slf4j
@Service
public class StuServiceImpl implements StuService {
    @Autowired
    private StuMapper stuMapper;
    private static final String STU_NUM_EXCEPTION = "学号长度不正确";
    /**
     * 新增学生
     * @param student
     */
    @Override
    public void addStu(Student student) {
        log.info("addStu() called with parameters => 【student = {}】", student);
        //检验
        checkStuNumPhoneIsExist(student);
        //修改默认属性
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        //执行添加
        stuMapper.insert(student);

    }

    /**
     * 根据ID删除学生
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delStuById(Integer id) {
        log.info("delStuById() called with parameters => 【id = {}】", id);
        stuMapper.deleteById(id);
    }

    /**
     * 根据ID查询
     * @param id id
     * @return 学生
     */
    @Override
    public Student selStuById(Integer id) {
        log.info("selStuById() called with parameters => 【id = {}】", id);
        return stuMapper.selectById(id);
    }

    /**
     * 修改学生
     * @param student
     */
    @Override
    public void updateStu(Student student) {
        log.info("updateStu() called with parameters => 【student = {}】", student);
        //检验
        checkStuNumPhoneIsExist(student);
        //修改默认属性
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.updateById(student);

    }

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
    @Override
    public PageBean selPage(Integer page, Integer pageSize, String name, Integer highestDegree, Integer classesId, Integer studentNumber) {
        log.info("selPage() called with parameters => 【page = {}】, 【pageSize = {}】, 【name = {}】, 【highestDegree = {}】, 【classesId = {}】, 【studentNumber = {}】",
                page, pageSize, name, highestDegree, classesId, studentNumber);
        //设置分页参数
        PageHelper.startPage(page, pageSize);
        //查询
        List<StudentDto> studentList = stuMapper.selectAll(name, highestDegree, classesId, studentNumber);
        Page<StudentDto> list = (Page<StudentDto>) studentList;
        return new PageBean(list.getTotal(), list.getResult());
    }

    /**
     * 扣分方法
     * @param student
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deductionsById(Student student) {
        //先获取当前
        Student select = stuMapper.selectById(student.getId());
        //修改违纪次数
        select.setDisciplineTimes(select.getDisciplineTimes() + 1);
        //执行扣分操作
        select.setDisciplineScore(select.getDisciplineScore() + student.getDisciplineScore());
        //执行修改
        stuMapper.updateById(select);
    }

    /**
     * 检验学号和手机号是否重复
     */
    private void checkStuNumPhoneIsExist(Student student) {
        //检验学号
        Student s = stuMapper.selectByStuNum(student);
        if (Objects.nonNull(s) && !Objects.equals(student.getId(), s.getId())) {
            throw new StuNumIsExistExiception("学号重复");
        }
        //检验学号长度
        if (student.getStudentNumber().length() > 10) {
            throw new StuNumLenthExiception(STU_NUM_EXCEPTION);
        }
        //检验手机号
        Student s2 = stuMapper.selectByPhone(student);
        if (Objects.nonNull(s2) && student.getId() != s2.getId()) {
            throw new PhoneIsExistException("手机号重复");
        }
    }
}

package com.xrui.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xrui.exception.ClassesNameIsExistException;
import com.xrui.exception.ClassesNumberIsExistException;
import com.xrui.mapper.ClassMapper;
import com.xrui.mapper.StuMapper;
import com.xrui.pojo.Classes;
import com.xrui.pojo.PageBean;
import com.xrui.pojo.Student;
import com.xrui.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 17:10
 * @description:
 */
@Service
@Slf4j
public class ClassesServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private StuMapper stuMapper;


    /**
     * 添加班级
     *
     * @param classes
     */
    @Override
    public void addClasses(Classes classes) {
        //检验班级名称是否重复
        checkClassesNameIsExist(classes);
        //检验教室号是否占用
        checkClassesRoomIsExist(classes);
        //修改属性
        classes.setCreateTime(LocalDateTime.now());
        classes.setUpdateTime(LocalDateTime.now());
        //直接插入
        classMapper.insert(classes);
    }

    /**
     * 根据ID删除班级
     * 同时删除改班级下的所有学员
     * 由于同时有两个操作，所以要开启事务
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delClaById(Integer id) {
        log.info("delClaById() called with parameters => 【id = {}】", id);
        //删除班级
        classMapper.deleteById(id);
        //将对应的学生修改
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getClassesId, id);
        List<Student> studentList = stuMapper.selectList(wrapper);
        for (Student student : studentList) {
            student.setClassesId(0);
            stuMapper.updateById(student);
        }

    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public Classes selClaById(Integer id) {
        log.info("selClaById() called with parameters => 【id = {}】", id);
        Classes classes = classMapper.selectById(id);
        return classes;
    }

    /**
     * 修改方法
     * @param classes
     */
    @Override
    public void update(Classes classes) {
        //检验名称是否合法
        checkClassesNameIsExist(classes);
        //检验教室号是否占用
        checkClassesRoomIsExist(classes);
        //修改修改时间
        classes.setUpdateTime(LocalDateTime.now());
        classMapper.updateById(classes);
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @param begin
     * @param end
     * @return
     */
    @Override
    public PageBean pageSel(Integer page, Integer pageSize, String name, LocalDate begin, LocalDate end) {
        //设置分页参数
        Page<Classes> classes = PageHelper.startPage(page, pageSize);
        //执行查询
        List<Classes> classesList = classMapper.getPage(name, begin, end);
        PageInfo<Classes> of = PageInfo.of(classesList);
        //3.返回结果
        return new PageBean(of.getTotal(), of.getList());
    }

    /**
     * 检验班级名称
     */
    private void checkClassesNameIsExist(Classes classes) {
        log.info("checkClassesNameIsExist() called with parameters => 【classes = {}】", classes);
        Classes c = classMapper.selectByName(classes);
        if (Objects.nonNull(c) && !classes.getId().equals(c.getId())) {
            log.error("checkClassesNameIsExist() called with exception => 【classes = {}】", classes);
            throw new ClassesNameIsExistException("班级名称已经存在");
        }
    }

    /**
     * 检验教室是否符合规格
     */
    public void checkClassesRoomIsExist(Classes classes) {
        log.info("checkClassesRoomIsExist() called with parameters => 【classes = {}】", classes);
        //查询全部当前教室的班级
        LambdaQueryWrapper<Classes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Classes::getClassesNumber, classes.getClassesNumber());
        List<Classes> classesList = classMapper.selectList(wrapper);
        //遍历这个集合
        for (Classes cla : classesList) {
            if (Objects.equals(cla.getId(), classes.getId())) {
                return;
            }
            //如果遍历出来的教室，结课时间是在传进来的的班级开课时间之后则报错，
            if (cla.getFinishTime().isAfter(classes.getStartTime())) {
                throw new ClassesNumberIsExistException("班级已经被占用！");
            }
        }
    }
}

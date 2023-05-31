package com.xrui.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xrui.exception.DeptNameIsExistException;
import com.xrui.mapper.DeptMapper;
import com.xrui.mapper.EmpMapper;
import com.xrui.pojo.Dept;
import com.xrui.pojo.Emp;
import com.xrui.service.DeptService;
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
 * @date 2023/5/25 9:51
 * @description:
 */
@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    /**
     * 私有化Mapper层
     */
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    /**
     * 查询所有部门
     *
     * @return
     */
    @Override
    public List<Dept> selAllDept() {
        return deptMapper.selectList(null);
    }

    /**
     * 根据ID删除部门
     *
     * @param id 需要执行两步操作，所以需要开启事务
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delById(Integer id) {
        log.info("delById() called with parameters => 【id = {}】", id);
        //删除部门的时候，同时也要删除所有该部门下的员工
        deptMapper.deleteById(id);
        LambdaQueryWrapper<Emp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Emp::getDeptId, id);
        empMapper.delete(wrapper);
    }

    /**
     * 添加部门
     *
     * @param
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDept(Dept dept) {
        log.info("addDept() called with parameters => 【dept = {}】", dept);
        //调用检验名称方法
        checkDeptNameIsExist(dept);
        //修改默认属性（创建时间和修改时间）
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //调用插入方法
        deptMapper.insert(dept);
    }

    /**
     * 根据ID查询部门
     *
     * @param id
     * @return
     */
    @Override
    public Dept selById(Integer id) {
        log.info("selById() called with parameters => 【id = {}】", id);
        return deptMapper.selectById(id);
    }

    /**
     * 修改部门
     *
     * @param dept
     */
    @Override
    public void updateDept(Dept dept) {
        log.info("updateDept() called with parameters => 【dept = {}】", dept);
        //调用检验方法
        checkDeptNameIsExist(dept);
        //没抛异常说明可以修改
        deptMapper.updateById(dept);
    }

    /**
     * 检验部门名称是否重复
     */
    private void checkDeptNameIsExist(Dept dept) {
        log.info("checkDeptNameIsExist() called with parameters => 【dept = {}】", dept);
        //根据部门名称查询
        Dept d = deptMapper.selectbyName(dept.getName());
        if (Objects.nonNull(d)) {
            log.error("checkDeptNameIsExist() called with exception => 【dept = {}】", dept);
            throw new DeptNameIsExistException();
        }
    }
}

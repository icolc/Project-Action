package com.xrui.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xrui.exception.UserNameIsExistException;
import com.xrui.mapper.EmpMapper;
import com.xrui.pojo.Emp;
import com.xrui.pojo.PageBean;
import com.xrui.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 11:19
 * @description:
 */
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    /**
     * 私有mapper层
     */
    @Autowired
    private EmpMapper empMapper;

    /**
     * 分页+模糊查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     */
    @Override
    public PageBean selPage(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        log.info("selPage() called with parameters => 【page = {}】, 【pageSize = {}】, 【name = {}】, 【gender = {}】, 【begin = {}】, 【end = {}】", page, pageSize, name, gender, begin, end);
        //设置分页参数
        IPage iPage = new Page(page, pageSize);
        //动态条件
        LambdaQueryWrapper<Emp> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(name), Emp::getName, name)
                .eq(Objects.nonNull(gender), Emp::getGender, gender)
                .ge(Objects.nonNull(begin), Emp::getEntrydate, begin)
                .le(Objects.nonNull(end), Emp::getEntrydate, end);
        //执行查询
        List<Emp> list = empMapper.selectList(wrapper);
        return new PageBean(iPage.getTotal(), list);
    }

    /**
     * 根据ID删除
     *
     * @param ids
     */
    @Override
    public void delById(List<Integer> ids) {
        log.info("delById() called with parameters => 【ids = {}】", ids);
        empMapper.deleteBatchIds(ids);
    }

    /**
     * 添加员工
     *
     * @param emp
     */
    @Override
    public void addEmp(Emp emp) {
        log.info("addEmp() called with parameters => 【emp = {}】", emp);
        //调用检验方法
        checkEmpUsernameIsExist(emp);
        //没出异常就进行新增
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public Emp getById(Integer id) {
        log.info("getById() called with parameters => 【id = {}】", id);
        return empMapper.selectById(id);
    }

    /**
     * 修改
     *
     * @param emp
     */
    @Override
    public void updateById(Emp emp) {
        log.info("updateById() called with parameters => 【emp = {}】", emp);
        //调用检验方法
        checkEmpUsernameIsExist(emp);
        //没出异常就继续修改
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
    }

    /**
     * 根据名字和密码查询
     *
     * @param emp
     * @return
     */
    @Override
    public Emp selectByNamePassword(Emp emp) {
        log.info("selectByNamePassword() called with parameters => 【emp = {}】", emp);
        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        wrapper.eq("username", emp.getUsername())
                .eq("password", emp.getPassword());
        Emp one = empMapper.selectOne(wrapper);
        return one;
    }


    /**
     * 检验员工用户名是否重复
     */
    private void checkEmpUsernameIsExist(Emp emp) {
        log.info("checkEmpUsernameIsExist() called with parameters => 【emp = {}】", emp);
        //调用检验语句
        Emp e = empMapper.selectByUsername(emp);
        if (Objects.nonNull(e) && !Objects.equals(e.getId(), emp.getId())) {
            log.error("checkEmpUsernameIsExist() called with exception => 【emp = {}】", emp, e);
            throw new UserNameIsExistException();
        }
    }

}

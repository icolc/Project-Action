package com.xrui.controlle;

import com.xrui.anno.Log;
import com.xrui.pojo.PageBean;
import com.xrui.pojo.Student;
import com.xrui.service.StuService;
import com.xrui.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 17:45
 * @description: 学生表示层
 */
@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
    /**
     * 私有化service层
     */
    @Autowired
    private StuService service;

    /**
     * 新增学生
     * @param student 要新增的学生信息
     * @return 统一的响应结果
     */
    @Log
    @PostMapping
    public Result addStu(@RequestBody Student student) {
        //调用新增方法
        service.addStu(student);
        return Result.success();
    }

    /**
     * 根据ID删除学生
     * @param id 传进来的id
     * @return 统一的响应结果
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delStuById(@PathVariable Integer id) {
        //调用删除方法
        service.delStuById(id);
        return Result.success();
    }

    /**
     * 根据ID获取学生
     * @param id 接收的id
     * @return 返回查到的信息
     */
    @GetMapping("/{id}")
    public Result selStuById(@PathVariable Integer id) {
        //调用查询方法
        Student student = service.selStuById(id);
        return Result.success(student);
    }

    /**
     * 修改学生方法
     * @param student 接收的学生信息
     * @return 统一的响应结果
     */
    @Log
    @PutMapping
    public Result updateStu(@RequestBody Student student) {
        log.info("updateStu() called with parameters => 【student = {}】", student);
        //调用修改
        service.updateStu(student);
        return Result.success();
    }

    /**
     * 分页查询
     * @param page 当前页数
     * @param pageSize 每页数量
     * @param name 名字
     * @param highestDegree 学历
     * @param classesId 班级ID
     * @param studentNumber 学号
     * @return 满足条件的信息
     */
    @GetMapping
    public Result pageSel(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           String name, Integer highestDegree, Integer classesId, Integer studentNumber) {
        log.info("pageSel() called with parameters => 【page = {}】, 【pageSize = {}】, 【name = {}】, 【highestDegree = {}】, " +
                "【classesId = {}】, 【studentNumber = {}】", page, pageSize, name, highestDegree, classesId, studentNumber);
        //调用分页查询方法
        PageBean pageBean = service.selPage(page, pageSize, name, highestDegree, classesId, studentNumber);
        return Result.success(pageBean);
    }

    /**
     * 违纪扣分
     * @param student 传进来的学生信息
     * @return 统一的响应结果
     */
    @Log
    @PutMapping("/deductions")
    public Result deductionsStu(@RequestBody Student student) {
        log.info("deductionsStu() called with parameters => 【student = {}】", student);
        //调用扣分方法
        service.deductionsById(student);
        return Result.success();
    }
}

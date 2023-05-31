package com.xrui.controlle;

import com.xrui.anno.Log;
import com.xrui.pojo.Classes;
import com.xrui.pojo.PageBean;
import com.xrui.service.ClassService;
import com.xrui.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 17:08
 * @description: 班级controller层
 */
@Slf4j
@RestController
@RequestMapping("/classes")
public class ClassesController {

    /**
     * 私有化service层
     */
    @Autowired
    private ClassService classService;

    /**
     * 新增班级
     *
     * @param classes   要新增的班级信息
     * @return  统一的响应结果
     */
    @Log
    @PostMapping
    public Result addClasses(@RequestBody Classes classes) {
        log.info("addClasses() called with parameters => 【classes = {}】", classes);
        //调用添加方法
        classService.addClasses(classes);
        //成功返回
        return Result.success();
    }

    /**
     * 根据ID删除
     *
     * @param id 传进来的id
     * @return 统一的响应结果
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delClaById(@PathVariable Integer id) {
        log.info("delClaById() called with parameters => 【id = {}】", id);
        //调用删除方法
        classService.delClaById(id);
        //成功返回
        return Result.success();
    }

    /**
     * 根据id获取
     *
     * @param id    传进来的id
     * @return 统一的响应结果
     */
    @GetMapping("/{id}")
    public Result selClaById(@PathVariable Integer id) {
        log.info("selClaById() called with parameters => 【id = {}】", id);
        //调用查询方法
        Classes classes = classService.selClaById(id);
        //成功返回班级信息
        return Result.success(classes);
    }

    /**
     * 修改班级
     * @param classes   要修改的班级信息
     * @return  统一的返回结果
     */
    @Log
    @PutMapping
    public Result updateClasses(@RequestBody Classes classes) {
        log.info("updateClasses() called with parameters => 【classes = {}】", classes);
        //调用修改方法
        classService.update(classes);
        //成功返回
        return Result.success();
    }

    /**
     * 分页查询
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, LocalDate begin, LocalDate end) {
        log.info("page() called with parameters => 【page = {}】, 【pageSize = {}】, 【name = {}】, 【begin = {}】, 【end = {}】", page, pageSize, name, begin, end);
        //调用分页查询，传入页码，每页大小，名字，开始时间，结束时间
        PageBean pageBean = classService.pageSel(page, pageSize, name, begin, end);
        //返回这PageBean
        return Result.success(pageBean);
    }
}

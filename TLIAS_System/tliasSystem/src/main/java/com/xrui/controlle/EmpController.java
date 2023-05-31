package com.xrui.controlle;

import com.xrui.anno.Log;
import com.xrui.pojo.Emp;
import com.xrui.pojo.PageBean;
import com.xrui.service.EmpService;
import com.xrui.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 11:09
 * @description: 员工响应类
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    /**
     * 私有化service层
     */
    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     * @param page  页码
     * @param pageSize 每页条数
     * @param name 名字
     * @param gender 性别
     * @param begin 开始时间
     * @param end 结束时间
     * @return 返回满足条件的所有信息
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "5") Integer pageSize,
                        String name, Integer gender,
                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("page() called with parameters => 【page = {}】, 【pageSize = {}】, 【name = {}】, 【gender = {}】, 【begin = {}】, 【end = {}】", page, pageSize, name, gender, begin, end);
        //调用service层的查询方法，入参：页数，每页数量，名字，性别，开始时间，结束时间
        PageBean pageBean = empService.selPage(page, pageSize, name, gender, begin, end);
        //把查询到的统一响应回去
        return Result.success(pageBean);
    }

    /**
     * 根据ID列表批量删除
     * @param ids 传进来的id列表
     * @return 统一的响应结果
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result delById(@PathVariable List<Integer> ids) {
        log.info("批量删除中....ids:{}", ids);
        //调用service层的根据ID删除方法
        empService.delById(ids);
        //统一响应结果
        return Result.success();
    }

    /**
     * 添加员工
     * @param emp 要添加的员工信息
     * @return 统一返回结果
     */
    @Log
    @PostMapping
    public Result addEmp(@RequestBody Emp emp) {
        log.info("添加员工中...");
        //调用service层的添加员工方法，入参:员工信息
        empService.addEmp(emp);
        return Result.success();
    }

    /**
     * 根据ID查询信息方法
     * @param id 传进来的id
     * @return 返回该id下的信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询员工中，被查询的id为：{}", id);
        //调用service 层的根据ID查询信息方法
        Emp byId = empService.getById(id);
        return Result.success(byId);
    }

    /**
     * 根据ID删除的方法
     * @param emp 传进来的信息
     * @return 返回统一的响应结果
     */
    @Log
    @PutMapping
    public Result updateById(@RequestBody Emp emp) {
        empService.updateById(emp);
        return Result.success();
    }

}

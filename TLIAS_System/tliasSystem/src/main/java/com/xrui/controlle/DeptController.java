package com.xrui.controlle;

import com.xrui.anno.Log;
import com.xrui.pojo.Dept;
import com.xrui.service.DeptService;
import com.xrui.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 9:46
 * @description: 部门controller层
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    /**
     * 私有化service层
     */
    @Autowired
    private DeptService deptService;

    /**
     * 查询所有部门
     */
    @GetMapping
    public Result selAllDept() {
        log.info("查询所有部门中...");
        //调用service层的查询所有方法中
        List<Dept> deptList = deptService.selAllDept();
        //返回响应查到的这个集合
        return Result.success(deptList);
    }

    /**
     * 根据ID删除
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delByID(@PathVariable Integer id) {
        log.info("根据ID删除部门中...{}", id);
        //调用service层的根据ID删除部门方法
        deptService.delById(id);
        //成功返回统一响应结果
        return Result.success();
    }

    /**
     * 新增部门
     */
    @Log
    @PostMapping
    public Result addDept(@RequestBody Dept dept) {
        log.info("新增部门中,部门名字为:{}", dept.getName());
        //调用service层的新增方法
        deptService.addDept(dept);
        //成功返回统一响应结果
        return Result.success();
    }

    /**
     * 根据Id查询部门信息
     * @param id 传进来的id
     * @return 返回该ID对应的部门信息
     */
    @GetMapping("/{id}")
    public Result selDeptById(@PathVariable Integer id) {
        log.info("selDeptById() called with parameters => 【id = {}】", id);
        //调用service层的根据ID查询方法
        Dept dept = deptService.selById(id);
        //把返回的部门信息响应回去
        return Result.success(dept);
    }

    /**
     * 根据Id修改信息
     * @param dept 传进来的部门信息
     * @return 统一响应结果
     */
    @Log
    @PutMapping
    public Result updateDept(@RequestBody Dept dept) {
        log.info("updateDept() called with parameters => 【dept = {}】", dept);
        //调用service层的根据ID修改方法，入参：dept
        deptService.updateDept(dept);
        //统一响应结果
        return Result.success();
    }


}

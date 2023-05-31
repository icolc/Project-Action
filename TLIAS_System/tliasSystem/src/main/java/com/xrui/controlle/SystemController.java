package com.xrui.controlle;

import com.xrui.mapper.EmpMapper;
import com.xrui.mapper.StuMapper;
import com.xrui.pojo.dto.EmpGender;
import com.xrui.pojo.dto.StudentHigDegree;
import com.xrui.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/30 15:26
 * @description: 系统表示层
 */
@RestController
@RequestMapping
public class SystemController {

    /**
     * 私有员工
     */
    @Autowired
    private EmpMapper empMapper;
    /**
     * 私有学生
     */
    @Autowired
    private StuMapper stuMapper;

    /**
     * 获取员工性别
     * @return 统计的信息
     */
    @GetMapping("/emps/count")
    public Result selectAllGender() {
        List<EmpGender> empGenders = empMapper.selectCountEmp();
        for (EmpGender empGender : empGenders) {
            if (Objects.equals(empGender.getName(), "1")) {
                empGender.setName("男");
            } else {
                empGender.setName("女");
            }
        }
        return Result.success(empGenders);
    }

    /**
     * 获取学生学历
     * @return 统计的信息
     */
    @GetMapping("/student/counts")
    public Result selectAllStudentHighestDegree() {
        List<StudentHigDegree> studentList = stuMapper.selectAllStudentHighestDegree();
        for (StudentHigDegree studentHigDegree : studentList) {
            if (Objects.equals(studentHigDegree.getName(), "1")) {
                studentHigDegree.setName("初中");
            } else if (Objects.equals(studentHigDegree.getName(), "2")) {
                studentHigDegree.setName("高中");
            } else if (Objects.equals(studentHigDegree.getName(), "3")) {
                studentHigDegree.setName("大专");
            } else if (Objects.equals(studentHigDegree.getName(), "4")) {
                studentHigDegree.setName("本科");
            } else if (Objects.equals(studentHigDegree.getName(), "5")) {
                studentHigDegree.setName("硕士");
            } else {
                studentHigDegree.setName("博士");
            }
        }
        return Result.success(studentList);
    }
}

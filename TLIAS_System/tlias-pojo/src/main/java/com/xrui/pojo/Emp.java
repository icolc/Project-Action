package com.xrui.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer id; //id
    private String username; //用户名
    private String password; //密码
    private String name; //姓名
    private Integer gender; //性别
    private String image; //头像
    private Short job; //职位：1班主任 2讲师 3 学生主管 4教研主任 5咨询师
    private LocalDate entrydate; //入职日期
    private Integer deptId; //部门ID
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}

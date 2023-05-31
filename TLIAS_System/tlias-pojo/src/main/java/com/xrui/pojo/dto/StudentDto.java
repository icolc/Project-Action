package com.xrui.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/29 19:09
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Integer id;
    private String name;
    private String studentNumber;
    private String classesName;
    private Integer gender;
    private String phone;
    private Integer highestDegree;
    private Integer classesId;
    private Integer disciplineTimes;
    private Integer disciplineScore;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

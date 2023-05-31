package com.xrui.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 17:48
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private String studentNumber;
    private Integer gender;
    private String phone;
    private Integer highestDegree;
    private Integer classesId;
    private Integer disciplineTimes;
    private Integer disciplineScore;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

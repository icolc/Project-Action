package com.xrui.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/26 11:41
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassesBean {

    private Integer id;
    private String name;
    private String classesNumber;
    private LocalDate startTime;
    private LocalDate finishTime;
    private String empName;
}

package com.xrui.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 9:19
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classes {
    private Integer id;
    private String name;
    private String classesNumber;
    private LocalDate startTime;
    private LocalDate finishTime;
    private Integer empId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}

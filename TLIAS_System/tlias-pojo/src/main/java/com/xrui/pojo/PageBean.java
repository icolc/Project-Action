package com.xrui.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 15:27
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    private Long total;
    private List rows;
}

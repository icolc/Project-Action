package com.xrui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xrui.pojo.Classes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 17:10
 * @description:
 */
@Mapper
public interface ClassMapper extends BaseMapper<Classes> {

    List<Classes> getPage(String name, LocalDate begin, LocalDate end);

    @Select("select * from classes where name = #{name};")
    Classes selectByName(Classes classes);
}

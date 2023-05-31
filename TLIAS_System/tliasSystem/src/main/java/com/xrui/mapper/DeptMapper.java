package com.xrui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xrui.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 10:11
 * @description:
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {

    @Select("select * from dept where name = #{name}; ")
    Dept selectbyName(String name);
}

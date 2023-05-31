package com.xrui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xrui.pojo.Emp;
import com.xrui.pojo.dto.EmpGender;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 14:24
 * @description:
 */
@Mapper
public interface EmpMapper extends BaseMapper<Emp> {

    @Select("select * from emp where username = #{username} ;")
    Emp selectByUsername(Emp emp);


    List<EmpGender> selectCountEmp();
}

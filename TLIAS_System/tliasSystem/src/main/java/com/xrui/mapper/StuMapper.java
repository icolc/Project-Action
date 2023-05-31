package com.xrui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xrui.pojo.Student;
import com.xrui.pojo.dto.StudentDto;
import com.xrui.pojo.dto.StudentHigDegree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 17:47
 * @description:
 */
@Mapper
public interface StuMapper extends BaseMapper<Student> {


    @Select("select * from student where student_number = #{studentNumber} ;")
    Student selectByStuNum(Student student);

    @Select("select * from student where phone = #{phone} ;")
    Student selectByPhone(Student student);

    /**
     * 分页查询
     * @param name
     * @param highestDegree
     * @param classesId
     * @param studentNumber
     * @return
     */
    List<StudentDto> selectAll(String name, Integer highestDegree, Integer classesId, Integer studentNumber);

    List<StudentHigDegree> selectAllStudentHighestDegree();
}

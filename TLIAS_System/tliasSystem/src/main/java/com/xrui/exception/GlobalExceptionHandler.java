package com.xrui.exception;

import com.xrui.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 10:27
 * @description: 异常类
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String USERNAME_IS_EXIST="用户名重复,请检查后重试";
    private static final String CLASSES_NAME_IS_EXIST="班级名称重复,请检查后重试";
    private static final String DEPT_NAME_IS_EXIST="部门名称重复,请检查后重试";
    private static final String STU_NUM_IS_EXIST="学号重复,请检查后重试";
    private static final String PHONE_IS_EXIST="手机号重复,请检查后重试";
    private static final String STU_NUM_EXCEPTION="学号长度不正确";
    private static final String SYSTEM_EXCEPTION="系统出小差了~请稍后再试哦~";
    private static final String CLASSES_NUM_EXIST="教室号已经被占用！请重新选择吧!";
    /**
     * 用户名重复异常
     * @param e
     * @return
     */
    @ExceptionHandler(UserNameIsExistException.class)
    private Result userNameException(Exception e) {
        log.error("userNameException() called with exception => 【e = {}】",e,e);
        return Result.error(USERNAME_IS_EXIST);
    }

    /**
     * 部门名称重复i
     * @param e
     * @return
     */
    @ExceptionHandler(DeptNameIsExistException.class)
    private Result DeptNameIsExistException(Exception e){
        log.error("DeptNameIsExistException() called with exception => 【e = {}】",e,e);
        return Result.error(DEPT_NAME_IS_EXIST);
    }

    /**
     * 班级名称重复异常
     * @param e
     * @return
     */
    @ExceptionHandler(ClassesNameIsExistException.class)
    private Result classesNameIsExist(Exception e){
        log.error("classesNameIsExist() called with exception => 【e = {}】",e,e);
        return Result.error(CLASSES_NAME_IS_EXIST);
    }

    /**
     * 学号重复异常
     * @param e
     * @return
     */
    @ExceptionHandler(StuNumIsExistExiception.class)
    private Result stuNumIsExistExiception(Exception e){
        log.error("stuNumIsExistExiception() called with exception => 【e = {}】",e,e);
        return Result.error(STU_NUM_IS_EXIST);
    }

    /**
     * 手机号重复异常
     * @param e
     * @return
     */
    @ExceptionHandler(PhoneIsExistException.class)
    private Result phoneIsExistException(Exception e){
        log.error("phoneIsExistException() called with exception => 【e = {}】",e,e);;
        return Result.error(PHONE_IS_EXIST);
    }

    /**
     * 学号长度不正确
     * @param e
     * @return
     */
    @ExceptionHandler(StuNumLenthExiception.class)
    private Result StuNumLenthException(Exception e){
        log.error("StuNumLenthException() called with exception => 【e = {}】",e,e);
        return Result.error(STU_NUM_EXCEPTION);
    }

    /**
     * 教室号已经被占用
     */
    @ExceptionHandler(ClassesNumberIsExistException.class)
    private Result classesNameIsExistException(Exception e){
        log.error("classesNameIsExistException() called with exception => 【e = {}】",e,e);
        return Result.error(CLASSES_NUM_EXIST);
    }

    /**
     * 其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    private Result exception(Exception e){
        log.error("exception() called with exception => ",e);
        return Result.error(SYSTEM_EXCEPTION);
    }
}

package com.xrui.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/26 14:37
 * @description:
 */
@Component
@Aspect
@Slf4j
public class TimeAspect {

    /**
     * 统计所有方法的耗时
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.xrui.controlle.*.*(..))")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.记录当前
        long start = System.currentTimeMillis();
        //2.执行
        Object o = joinPoint.proceed();
        //3.记录当前
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + "耗时：" + (end - start) + "毫秒");
        return o;
    }

    /**
     * 将增删改的相关接口的操作日志保存到数据库：操作表中
     */

}

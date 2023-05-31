package com.xrui.aop;

import com.alibaba.fastjson.JSONObject;
import com.xrui.mapper.OperateLogMapper;
import com.xrui.pojo.OperateLog;
import com.xrui.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/27 15:32
 * @description: 统计增删改日志，并存到数据库当中
 */
@Aspect
@Slf4j
@Component
public class LogAspect {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Around("@annotation(com.xrui.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1.1 从请求头中获取token
        String token = httpServletRequest.getHeader("token");
        // 1.2 解析token获取token中存储的id
        Claims claims = JwtUtils.parseJWT(token);
        Integer operateUser = (Integer) claims.get("id");
        // 2. 操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        // 3. 执行目标方法的全类名
        String className = joinPoint.getTarget().getClass().getName();
        // 4. 执行方法名
        String methodName = joinPoint.getSignature().getName();
        // 5. 获取入参
        String methodParams = Arrays.toString(joinPoint.getArgs());
        //6.执行方法并获取返回值
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        // 7. 返回值
        String returnValue = JSONObject.toJSONString(result);
        // 8. 方法执行时长
        long costTime = end - start;
        // 9. 构建日志实体
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        // 10. 将数据写入库中
        operateLogMapper.insert(operateLog);
        log.info("记录操作日志:{}",operateLog);
        return  result;
    }
}

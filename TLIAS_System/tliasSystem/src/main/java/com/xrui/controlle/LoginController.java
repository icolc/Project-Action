package com.xrui.controlle;

import com.xrui.pojo.Emp;
import com.xrui.service.EmpService;
import com.xrui.utils.JwtUtils;
import com.xrui.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 10:10
 * @description: 登录表示层
 */
@Slf4j
@RestController
public class LoginController {
    /**
     * 私有化员工service层
     */
    @Autowired
    private EmpService empService;
    /**
     * 定义的常量
     */
    private static final String USER_PASSWORD_EXCEPTION = "用户名或密码错误！";
    /**
     * 页面登录方法
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录中。。。用户名：{}，密码：{}",emp.getUsername(),emp.getPassword());
        Emp p =empService.selectByNamePassword(emp);
        //查询到了成功
        if (p != null){
            //定义一个Map集合，用来存储这个用户信息
            Map<String, Object> clamis = new HashMap<>();
            clamis.put("id",p.getId());
            clamis.put("username",p.getUsername());
            clamis.put("name",p.getName());
            //根据这个集合生产JWT令牌
            String jwt = JwtUtils.generateJwt(clamis);
            log.info("JWT令牌为:{}",jwt);
            //响应给前端
            return Result.success(jwt);
        }
        //登录失败，响应错误信息
        return Result.error(USER_PASSWORD_EXCEPTION);
    }
}

package com.xrui.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.xrui.utils.JwtUtils;
import com.xrui.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 11:41
 * @description: 拦截器
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    private static final String NOT_LOGIN = "NOT_LOGIN";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头
        String url = request.getRequestURI().toString();
        //判断是否包含login
        if (url.contains("/login")) {
            log.info("登录方法，放行！");
            return true;
        }
        //判断是否包含token
        String token = request.getHeader("token");
        //判断是否存在
        if (!StringUtils.hasLength(token)) {
            Result error = Result.error(NOT_LOGIN);
            String s = JSONObject.toJSONString(error);
            response.getWriter().write(s);
            return false;
        } else {
            //解析令牌
            try {
                JwtUtils.parseJWT(token);
                //成功
                return true;
            } catch (Exception e) {
                //失败
                Result error = Result.error(NOT_LOGIN);
                String s = JSONObject.toJSONString(error);
                response.getWriter().write(s);
                return false;
            }
        }
    }
}

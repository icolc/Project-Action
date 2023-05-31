package com.xrui.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author NXRUI
 * @version 1.0
 * @date 2023/5/25 11:36
 * @description: MP配置的拦截器
 */
@Configuration
public class MPConfig {

    @Bean
    public MybatisPlusInterceptor mpInterceptor(){
        //定义拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //添加具体的拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}

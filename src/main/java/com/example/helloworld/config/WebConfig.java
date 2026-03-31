package com.example.helloworld.config;

import com.example.helloworld.interceptor.JwtInterceptor;
import com.example.helloworld.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 一个类里只能有一个 addInterceptors 方法！
    // 两个拦截器在这里统一注册
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 1. 全局 JWT 拦截器：校验所有接口（除了登录/刷新）
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login",
                        "/user/info",   // <-- 必须放行！
                        "/user/add",
                        "/refreshToken",
                        "/error",
                        "/static/**",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                );
    }
}
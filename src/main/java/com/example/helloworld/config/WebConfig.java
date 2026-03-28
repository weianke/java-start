package com.example.helloworld.config;

import com.example.helloworld.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC 配置类
 * 作用：配置拦截器、跨域、视图解析器等 Web 相关设置
 * Configuration 标记这是一个配置类，Spring 启动时会自动加载
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 添加自定义拦截器
     * @param registry 拦截器注册器，用来注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 1. 注册 LoginInterceptor 拦截器
        // 2. addPathPatterns("/user/**")：只拦截 /user/ 开头的所有请求
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/user/**");
    }
}
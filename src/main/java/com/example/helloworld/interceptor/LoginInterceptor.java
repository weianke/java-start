package com.example.helloworld.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * 作用：在请求进入Controller之前，进行登录状态校验
 * 实现Spring MVC的HandlerInterceptor接口，完成拦截功能
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 预处理方法：在Controller方法执行之前调用
     * @param request 请求对象，用于获取请求信息、Session等
     * @param response 响应对象，用于返回结果、重定向等
     * @param handler 处理器对象（即Controller中的方法）
     * @return true：放行请求；false：拦截请求
     * @throws Exception 抛出异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 控制台打印，用于测试拦截器是否生效
        System.out.println("LoginInterceptor");

        // 目前返回false，表示拦截所有请求，不放行
        // 后续可在这里添加登录判断逻辑：已登录返回true，未登录返回false并跳转到登录页
        return true;
    }
}
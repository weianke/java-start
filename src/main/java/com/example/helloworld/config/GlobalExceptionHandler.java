package com.example.helloworld.config;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局统一异常处理（大厂标准）
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截：表单校验失败（@NotBlank @Size 等全部自动捕获）
     */
    @ExceptionHandler(BindException.class)
    public String handleValidException(BindException e) {
        // 获取第一条错误
        FieldError fieldError = e.getBindingResult().getFieldErrors().get(0);
        // 返回错误信息
        return fieldError.getDefaultMessage();
    }

    /**
     * 兜底：捕获所有未知异常（防止系统崩溃）
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return "服务器异常：" + e.getMessage();
    }
}
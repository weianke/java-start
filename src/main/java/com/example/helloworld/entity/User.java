package com.example.helloworld.entity;

import lombok.Data;

/**
 * 用户实体类
 * 对应前端登录/注册请求参数
 */
@Data // 自动生成 getter/setter/toString/equals/hashCode 等方法
public class User {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 登录成功后返回的 Token
     */
    private String token;
}
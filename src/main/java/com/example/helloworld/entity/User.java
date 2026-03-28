package com.example.helloworld.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户实体类
 * 对应前端登录/注册请求参数
 */
@Data // 自动生成 getter/setter/toString/equals/hashCode 等方法
@Schema(description = "用户实体")
public class User {

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 验证码
     */
    @Schema(description = "验证码")
    private String code;

    /**
     * 登录成功后返回的 Token
     */
    @Schema(description = "Token")
    private String token;
}
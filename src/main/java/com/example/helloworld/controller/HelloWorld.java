// 表示这个类是一个【控制器】，专门处理前端请求
package com.example.helloworld.controller;

// Spring 提供的 web 开发注解
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// 标记这个类 = 接口控制器（返回 JSON/字符串，不跳转页面）
@RestController
public class HelloWorld {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(@RequestBody Map<String, Object> map) {
        String name = map.get("name").toString();
        String phone = map.get("phone").toString();

        return "你好：" + name + " 手机号：" + phone;

    }

}
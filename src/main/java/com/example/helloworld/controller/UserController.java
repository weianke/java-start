package com.example.helloworld.controller;

import com.example.helloworld.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/user/{id}")
    public String getUserId(@PathVariable int id) {
        System.out.println(id);
        return "根据ID获取信息";
    }

    @PostMapping("/user/add")
    public String save(User user) {
        System.out.println(user);
        return "添加用户";
    }

    @PutMapping("/user/update")
    public String update(User user) {
        System.out.println(user);
        return "更新用户";
    }

    @DeleteMapping("/user/{id}")
    public String deleteById(@PathVariable int id) {
        System.out.println(id);
        return "根据ID删除用户";
    }
}

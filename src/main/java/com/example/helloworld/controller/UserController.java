package com.example.helloworld.controller;

import com.example.helloworld.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testUser")
@Tag(name = "测试案例用户模块")
public class UserController {

    @Operation(summary = "根据ID获取用户") // 接口说明
    @GetMapping("/{id}")
    public String getUserId(@PathVariable int id) {
        System.out.println(id);
        return "根据ID获取信息";
    }

    @Operation(summary = "添加用户") // 接口说明
    @PostMapping("/add")
    public String save(@RequestBody User user) {
        System.out.println(user);
        return "添加用户";
    }

    @Operation(summary = "更新用户") // 接口说明
    @PutMapping("/update")
    public String update(@RequestBody User user) {
        System.out.println(user);
        return "更新用户";
    }

    @Operation(summary = "删除用户") // 接口说明
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id) {
        System.out.println(id);
        return "根据ID删除用户";
    }
}

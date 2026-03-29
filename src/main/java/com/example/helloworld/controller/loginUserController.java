package com.example.helloworld.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.helloworld.common.result.Result;
import com.example.helloworld.dto.UserDTO;
import com.example.helloworld.entity.User;
import com.example.helloworld.mapper.UserMapper;
import com.example.helloworld.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "用户模块")
public class loginUserController {

    @Autowired
    private UserMapper userMapper;

    @Operation(summary = "查询用户") // 接口说明
    @GetMapping("/queryUser")
    public Result<List<User>> query() {
        List<User> list = userMapper.selectList(new QueryWrapper<>());
        System.out.println(list);
        return Result.success(list);
    }

    @Operation(summary = "新增用户")
    @PostMapping("/add")
    public Result<String> save(@Valid @RequestBody UserDTO dto) {
        // DTO 转实体
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setCode(dto.getCode());
        user.setToken(JwtUtil.createToken(dto.getUsername()));

        userMapper.insert(user);
        return Result.success(null);
    }


}

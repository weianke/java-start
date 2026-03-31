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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@Tag(name = "用户模块")
public class LoginUserController {

    @Autowired
    private UserMapper userMapper;


    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@Valid @RequestBody UserDTO dto) {

        String username = dto.getUsername();
        String password = dto.getPassword();

        // 1. 前端只传了 username + password，只能用 username 查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> userList = userMapper.selectList(queryWrapper);

        if (userList.isEmpty()) {
            return Result.error(400, "用户不存在");
        }

        // 2. 匹配密码，找到唯一用户
        User loginUser = null;
        // 流式查找：用户名 + 密码 同时匹配
        if (username != null && password != null) {
            loginUser = userList.stream()
                    .filter(user -> username.equals(user.getUsername())
                            && password.equals(user.getPassword()))
                    .findFirst()
                    .orElse(null);
        }

        if (loginUser == null) {
            return Result.error(400, "密码错误");
        }

        // 3. 拿到唯一 id → 生成 token
        String token = JwtUtil.createToken(loginUser.getUsername());

        // 4. 返回 id 作为唯一标识
        Map<String, Object> map = new HashMap<>();
        map.put("userId", loginUser.getId());
        map.put("username", loginUser.getUsername());
        map.put("password", loginUser.getPassword());
        map.put("token", token);

        return Result.success(map);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/user/info")
    public Result<Map<String, Object>> info(@RequestParam String token) {
        // 第一步：校验 token 是否有效
        boolean isValid = JwtUtil.validateToken(token);
        System.out.println("token 是否有效：" + isValid);

        if (!isValid) {
            return Result.error(401, "token无效或已过期");
        }

        // 第二步：解析用户名
        String username = JwtUtil.getUsername(token);
        System.out.println("解析出来的用户名：" + username);

        if (username == null) {
            return Result.error(401, "无法解析用户名");
        }

        String avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("avatar", avatar);

        return Result.success(map);
    }

    @Operation(summary = "查询用户") // 接口说明
    @GetMapping("/user/queryUser")
    public Result<List<User>> query() {
        List<User> list = userMapper.selectList(new QueryWrapper<>());
        System.out.println(list);
        return Result.success(list);
    }

    @Operation(summary = "新增用户")
    @PostMapping("/user/add")
    public Result<String> save(@Valid @RequestBody UserDTO dto) {
        // DTO 转实体
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setToken(JwtUtil.createToken(dto.getUsername()));

        userMapper.insert(user);
        return Result.success(null);
    }

    // 查询所有用户及其订单
    @Operation(summary = "查询用户所有订单")
    @GetMapping("user/{userId}")
    public Result<List<User>> getAllUserAndOrders(@PathVariable Long userId) {
        List<User> userList = userMapper.selectUserAndOrdersByUserId(userId);
        return Result.success(userList);
    }

}

package com.example.helloworld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.helloworld.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 1. 加 @Mapper 注解
// 2. 继承 BaseMapper<User>  →  核心！！！
@Mapper
public interface UserMapper extends BaseMapper<User> {

    // ======================= 重点 =======================
    // 这里 什么都不用写！！！
    // 原来的 @Select @Insert 全部删掉！！！
    // 字段？SQL？全都不用写！！！

    // 保留原来的查询方法
    @Select("select * from `user`")
    List<User> find();
}
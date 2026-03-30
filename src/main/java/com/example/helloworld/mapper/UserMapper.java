package com.example.helloworld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.helloworld.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

// 1. 加 @Mapper 注解
// 2. 继承 BaseMapper<User>  →  核心！！！
@Mapper
public interface UserMapper extends BaseMapper<User> {

    // ======================= 重点 =======================
    // 这里 什么都不用写！！！
    // 原来的 @Select @Insert 全部删掉！！！
    // 字段？SQL？全都不用写！！！

    // 查询用户及其所有订单
    @Select("select * from t_user")
    @Results(
            {
                    @Result(column = "id",property = "id"),
                    @Result(column = "username", property = "username"),
                    @Result(column = "password", property = "password"),
                    @Result(column = "code", property = "code"),
                    // 关键：通过用户id查询订单，并映射到orders字段
                    @Result(
                            column = "id", // 传入的列名：用户id
                            property = "orders", // 映射到User实体的orders字段
                            javaType = List.class,
                            many = @Many(select = "com.example.helloworld.mapper.OrderMapper.selectByUid")
                    )
            }
    )
    List<User> selectAllUserAndOrders();

    // 新增方法：根据用户ID查单个用户+订单（带参）
    @Select("select * from t_user where id = #{userId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "code", property = "code"),
            @Result(
                    column = "id",
                    property = "orders",
                    javaType = List.class,
                    many = @Many(select = "com.example.helloworld.mapper.OrderMapper.selectByUid")
            )
    })
    List<User> selectUserAndOrdersByUserId(Long userId);
}
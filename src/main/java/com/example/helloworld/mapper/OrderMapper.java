package com.example.helloworld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.helloworld.entity.Order;
import com.example.helloworld.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    // 根据用户id查询该用户的所有订单
    @Select("SELECT * FROM t_order WHERE uid = #{uid}")
    List<Order> selectByUid(Long uid);

    // 查询所有的订单，同时查询订单的用户
    @Select("select * from t_order")
    @Results(
            {
                    @Result(column = "id",property = "id"),
                    @Result(column = "orderTime",property = "orderTime"),
                    @Result(column = "total",property = "total"),
                    @Result(column = "uid",property = "uid"),
                    @Result(column = "uid",property = "user",javaType = User.class,
                        one = @One(select = "com.example.helloworld.mapper.UserMapper.selectById")
                    )

            }
    )
    List<Order> selectAllOrdersAndUser();
}
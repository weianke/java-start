package com.example.helloworld.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data // 自动生成 getter/setter/toString/equals/hashCode 等方法
@Schema(description = "订单实体")
@TableName("t_order") // 加这个！对应数据库表名
public class Order {


    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "id")
    private Integer id;

    /**
     * 订单日期
     */
    @Schema(description = "订单日期")
    // 自动格式化日期，返回给前端正常字符串
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate orderTime;

    /**
     * 订单金额
     */
    @Schema(description = "订单金额")
    private Integer total;

    /**
     * 订单uid
     */
    @Schema(description = "订单uid")
    private Long uid;


    /**
     * 订单对应的用户
     */
    @TableField(exist = false)
    private User user;
}

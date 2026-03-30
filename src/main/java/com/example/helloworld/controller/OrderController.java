package com.example.helloworld.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.helloworld.common.result.Result;
import com.example.helloworld.entity.Order;
import com.example.helloworld.mapper.OrderMapper;
import com.example.helloworld.util.PageUtil;
import com.example.helloworld.vo.PageQuery;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Tag(name = "订单模块")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    // 返回所有订单
    @GetMapping("/findAll")
    public Result<List<Order>> findAll() {
        List<Order> orderList = orderMapper.selectAllOrdersAndUser();
        return Result.success(orderList);
    }

    @PostMapping("/page")
    public Result<List<Order>> page(@RequestBody PageQuery query) {
        IPage<Order> page = PageUtil.page(query, orderMapper);
        return Result.pageSuccess(
                page
        );
    }

}

package com.example.helloworld.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.helloworld.common.result.Result;
import com.example.helloworld.entity.Order;
import com.example.helloworld.mapper.OrderMapper;
import com.example.helloworld.util.PageUtil;
import com.example.helloworld.vo.PageQuery;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "查询所有订单") // 接口说明
    @GetMapping("/findAll")
    public Result<List<Order>> findAll() {
        List<Order> orderList = orderMapper.selectAllOrdersAndUser();
        return Result.success(orderList);
    }

    // 分页查询
    @Operation(summary = "分页查询") // 接口说明
    @PostMapping("/page")
    public Result<List<Order>> page(@RequestBody PageQuery query) {
        IPage<Order> page = PageUtil.page(query, orderMapper);
        return Result.pageSuccess(
                page
        );
    }


    // 删除订单
    @Operation(summary = "删除订单") // 接口说明
    @PostMapping("/delete")
    public Result<Void> delete(@RequestBody Order order) {
        int rows = orderMapper.deleteById(order.getId());
        return rows > 0 ? Result.success() : Result.error(500,"删除失败，订单不存在");
    }

    // 更新订单
    @Operation(summary = "更新") // 接口说明
    @PostMapping("/update")
    public Result<Void> update(@RequestBody Order order) {
        int rows = orderMapper.updateById(order);
        return rows > 0 ? Result.success() : Result.error(500,"更新失败");
    }

}

package com.example.helloworld.common.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 大厂标准 RESTful 全局返回体
 * 所有接口 必须 返回这个类
 */
@Data
public class Result<T> implements Serializable {

    // 响应状态码
    private Integer code;

    // 响应消息
    private String message;

    // 响应数据
    private T data;

    // 时间戳（标准格式）
    private long timestamp;

    // ====================== 分页字段（仅分页接口赋值，普通接口不显示） ======================
    private Long total;    // 总条数
    private Long size;     // 每页条数
    private Long current;  // 当前页
    private Long pages;    // 总页数

    // 私有构造，强制使用静态方法构建
    private Result() {
        this.timestamp = System.currentTimeMillis();
    }

    // ====================== 成功返回 ======================
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    // ====================== ✅ 优化：极简分页方法（修复泛型） ======================
    public static <E> Result<List<E>> pageSuccess(IPage<E> page) {
        Result<List<E>> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(page.getRecords()); // 现在 List<E> 完美匹配 Result<List<E>>
        result.setTotal(page.getTotal());
        result.setSize(page.getSize());
        result.setCurrent(page.getCurrent());
        result.setPages(page.getPages());
        return result;
    }
    // ====================== 失败返回 ======================
    public static <T> Result<T> error(ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
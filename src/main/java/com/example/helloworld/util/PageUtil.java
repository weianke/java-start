package com.example.helloworld.util;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.helloworld.vo.PageQuery;

public class PageUtil {

    public static <T> IPage<T> page(PageQuery query, BaseMapper<T> mapper) {
        Page<T> page = new Page<>(query.getCurrent(), query.getSize());
        return mapper.selectPage(page, null);
    }
}
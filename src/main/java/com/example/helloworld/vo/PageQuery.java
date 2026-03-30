package com.example.helloworld.vo;

import lombok.Data;

@Data
public class PageQuery {
    private Long current = 1L;
    private Long size = 10L;
}
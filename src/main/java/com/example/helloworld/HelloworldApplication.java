// 所在包路径（必须和你的项目结构一致）
package com.example.helloworld;

// 导入 SpringBoot 启动核心类
import org.springframework.boot.SpringApplication;
// 导入 SpringBoot 自动配置注解
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 项目的**启动类 / 入口类**
 * 整个项目运行的起点
 */
@SpringBootApplication  // 核心注解：标记这是一个 SpringBoot 应用，开启自动配置
public class HelloworldApplication {

    // Java 程序固定入口：main 方法
    public static void main(String[] args) {
        // 启动 SpringBoot 项目
        // 参数：当前类.class，启动参数
        SpringApplication.run(HelloworldApplication.class, args);
    }

}
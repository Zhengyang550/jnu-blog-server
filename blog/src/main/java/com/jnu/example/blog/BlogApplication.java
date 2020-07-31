package com.jnu.example.blog;

import com.jnu.example.db.annotation.CoreMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *  @Author: zy
 *  @Date: 2020/4/14 22:40
 *  @Description: Spring Boot程序入口
 */
@SpringBootApplication(scanBasePackages={"com.jnu.example"})
@CoreMapperScan
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}

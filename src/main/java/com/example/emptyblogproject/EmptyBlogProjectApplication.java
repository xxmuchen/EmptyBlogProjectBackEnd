package com.example.emptyblogproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication

@MapperScan("com.example.emptyblogproject.mapper")
public class EmptyBlogProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmptyBlogProjectApplication.class, args);
    }

}

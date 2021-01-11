package com.javakc.luxiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.javakc.luxiang.mapper")
public class LuxiangApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuxiangApplication.class, args);
    }

}

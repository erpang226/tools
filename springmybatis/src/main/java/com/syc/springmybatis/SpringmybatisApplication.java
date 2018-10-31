package com.syc.springmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.syc.springmybatis.mapper")
public class SpringmybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmybatisApplication.class, args);
    }
}

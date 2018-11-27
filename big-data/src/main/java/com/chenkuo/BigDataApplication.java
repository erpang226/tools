package com.chenkuo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * 程序入口
 *
 * @author syc
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class BigDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigDataApplication.class, args);

    }
}

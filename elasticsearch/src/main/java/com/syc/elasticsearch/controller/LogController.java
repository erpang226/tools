package com.syc.elasticsearch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/logController")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/test")
    public String test() throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            logger.info("日志测试");

            logger.info("日志测试, next long: {}", new Random().nextLong());
            Thread.sleep(1000);
        }
        return "log test printed";
    }

}

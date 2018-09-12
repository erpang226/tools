package com.syc.sonar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements Myservice {

    private static final Logger logger = LoggerFactory.getLogger(MyServiceImpl.class);

    @Override
    public String test(int n) {
        if (n % 2 == 0) {
            for (int i = 0; i < 10; i++) {
                logger.info("我是偶数。。。");
            }
            return "偶数";
        } else {
            for (int i = 0; i < 10; i++) {
                logger.info("我是奇数。。。");
            }
            return "奇数";
        }
    }

    @Override
    public String test1(String name) {
        for (int i = 0; i < 10; i++) {
            logger.info("hello {}", name);
        }

        for (int i = 0; i < 10; i++) {
            logger.info("hello {}", name);
        }
        for (int i = 0; i < 10; i++) {
            logger.info("hello {}", name);
        }
        return "说完了";
    }
}

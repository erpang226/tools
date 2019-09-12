package com.syc.kydata.service.impl;

import com.syc.kydata.config.DirectReturn;
import com.syc.kydata.dto.ReturnData;
import com.syc.kydata.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestServiceImplTest {

    @Autowired
    private TestService testService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1() throws InvalidKeyException, NoSuchAlgorithmException {

        testService.register();
    }

    @Test
    public void test4() throws InvalidKeyException, NoSuchAlgorithmException {

        // [Content-Type:"application/json;charset=UTF-8", X-AK-KEY:"b2934678d6304059bb57528994c3e4cb", X-AK-PIN:"Ua1naaYIaXoS4Y4ZEia5J17vBnE=", X-AK-TS:"1568094260075"]>

        boolean b=testService.checkSign("1568094260075","9a7e8777770e4ed98ae1279d60abaa73","Ua1naaYIaXoS4Y4ZEia5J17vBnE=");
        System.out.println(b);


    }

    @Test
    public void test2() {

        String password = "123";

        if (password.matches("^.{4,19}$")) {
            System.out.println("密码不合法");
            throw new RuntimeException("密码不合法");
        }
        System.out.println("密码合法: " + password);


    }


    @Test
    public void test3() {

        redisTemplate.opsForValue().set("name","tom");

    }





}
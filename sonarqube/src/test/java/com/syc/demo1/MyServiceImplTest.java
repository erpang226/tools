package com.syc.demo1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MyServiceImplTest {

    @Autowired
    private Myservice myservice;

    @Test
    public void test() {
        myservice.test(2);
        myservice.test(1);

    }

    @Test
    public void test1() {
        Assert.assertEquals("说完了",myservice.test1("tom"));
    }


}
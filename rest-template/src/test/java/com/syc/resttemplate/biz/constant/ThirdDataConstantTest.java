package com.syc.resttemplate.biz.constant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ThirdDataConstantTest {


    @Test
    public void test() {
        ThirdDataConstant constant = ThirdDataConstant.init();
        System.out.println(constant.toString());

        System.out.println(ThirdDataConstant.QUERY_CONDITION);
    }


}
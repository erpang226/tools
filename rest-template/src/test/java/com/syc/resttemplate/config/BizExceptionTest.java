package com.syc.resttemplate.config;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BizExceptionTest {

    @Test
    public void getReturnEnum() {
        BizException be = new BizException(ReturnEnum.FAIL);

        System.out.println(be.getReturnEnum());
        System.out.println(be.getReturnObj());
        System.out.println(be.getReturnEnum());


        Throwable e = new Throwable();
        BizException be1 = new BizException(ReturnEnum.FAIL, e);
        System.out.println(be1.getMessage());

        BizException be2 = new BizException(ReturnEnum.FAIL, new JSONObject(), e);
        be2.setReturnObj("tom");
        System.out.println(be2.getReturnObj());

        BizException be3 = new BizException(ReturnEnum.FAIL, new JSONObject());
        System.out.println(be3.getMessage());
        System.out.println(ReturnEnum.getReturnEnum(ReturnEnum.ERROR_100001.getCode()));
    }


}
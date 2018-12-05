package com.syc.resttemplate.biz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.syc.resttemplate.biz.service.SjbService;
import com.syc.resttemplate.config.BizException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SjbServiceImplQueryTest {

    @Autowired
    private SjbService sjbService;


    @Test
    public void queryEnterpriseBasicInfoList() throws BizException {

        List<JSONObject> result = sjbService.queryEnterpriseBasicInfo("南京智享家信息科技有限公司", 0, "test");
        System.out.println("query result :" + result);
        System.out.println("query result size: " + result.size());
    }

    @Test
    public void queryDCDY() throws BizException {
        List<JSONObject> result = sjbService.queryDCDY("南京智享家信息科技有限公司", 0, "test");
        System.out.println("query result :" + result);
        System.out.println("query result size: " + result.size());
    }

    @Test
    public void queryXZCF() throws BizException {
        List<JSONObject> result = sjbService.queryXZCF("南京智享家信息科技有限公司", 0, "test");
        System.out.println("query result :" + result);
        System.out.println("query result size: " + result.size());
    }

    @Test
    public void queryJYYC() throws BizException {
        List<JSONObject> result = sjbService.queryJYYC("南京智享家信息科技有限公司", 0, "test");
        System.out.println("query result :" + result);
        System.out.println("query result size: " + result.size());
    }

    @Test
    public void queryKTGG() throws BizException {
        List<JSONObject> result = sjbService.queryKTGG("南京智享家信息科技有限公司", 0, "test");
        System.out.println("query result :" + result);
        System.out.println("query result size: " + result.size());
    }

    @Test
    public void queryBZXR() throws BizException {
        List<JSONObject> result = sjbService.queryBZXR("南京智享家信息科技有限公司", 0, "test");
        System.out.println("query result :" + result);
        System.out.println("query result size: " + result.size());
    }

    @Test
    public void queryFYGG() throws BizException {
        List<JSONObject> result = sjbService.queryFYGG("南京智享家信息科技有限公司", 0, "test");
        System.out.println("query result :" + result);
        System.out.println("query result size: " + result.size());
    }

    @Test
    public void queryFYPJ() throws BizException {
        List<JSONObject> result = sjbService.queryFYPJ("南京智享家信息科技有限公司", 0, "test");
        System.out.println("query result :" + result);
        System.out.println("query result size: " + result.size());
    }

    @Test
    public void querySXR() throws BizException {
        List<JSONObject> result = sjbService.querySXR("南京智享家信息科技有限公司", 1, "test");
        System.out.println("query result :" + result);
        System.out.println("query result size: " + result.size());
    }

    @Test
    public void querySMRZ() throws BizException {
        List<JSONObject> result = sjbService.querySMRZ("宋永昌", "320322198902265610", 0, "test");
        System.out.println("query result :" + result);
        System.out.println("query result size: " + result.size());
    }

    @Test
    public void queryGQCZ() throws BizException {
        List<JSONObject> result = sjbService.queryGQCZ("南京智享家信息科技有限公司", 0, "test");
        System.out.println("query result :" + result);
        System.out.println("query result size: " + result.size());
    }

    @Test
    public void querySFZSB() throws BizException {
        List<JSONObject> result = sjbService.querySFZSB("a021effe4e664e619fe7ebedfadcb882", 0, "test");
        System.out.println("query result :" + result);
        System.out.println("query result size: " + result.size());
    }

    @Test
    public void queryYHKSB() throws BizException {
        List<JSONObject> result = sjbService.queryYHKSB("61b8e5a9af234f658e3b101b6fde2ba6", 0, "test");
        System.out.println("query result :" + result);
        System.out.println("query result size: " + result.size());
    }


}
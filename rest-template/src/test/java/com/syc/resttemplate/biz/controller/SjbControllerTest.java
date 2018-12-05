package com.syc.resttemplate.biz.controller;

import com.alibaba.fastjson.JSONObject;
import com.syc.resttemplate.biz.service.SjbService;
import com.syc.resttemplate.config.BizException;
import com.syc.resttemplate.config.ReturnEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SjbControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private SjbService sjbService;


    @Test
    public void getEnterpriseBasicInfo() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(sjbService.getEnterpriseBasicInfo("南京智享家信息科技有限公司", "name", "0", "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getEnterpriseBasicInfo")
                .param("name", "南京智享家信息科技有限公司")
                .param("type", "name")
                .param("entType", "0")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(sjbService.getEnterpriseBasicInfo("南京智享家信息科技有限公司", "name", "0", "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getEnterpriseBasicInfo")
                .param("name", "南京智享家信息科技有限公司")
                .param("type", "name")
                .param("entType", "0")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getDCDY() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(sjbService.getDCDY("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getDCDY")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(sjbService.getDCDY("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getDCDY")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getXZCF() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(sjbService.getXZCF("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getXZCF")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(sjbService.getXZCF("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getXZCF")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getJYYC() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(sjbService.getJYYC("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getJYYC")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(sjbService.getJYYC("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getJYYC")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getKTGG() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(sjbService.getKTGG("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getKTGG")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(sjbService.getKTGG("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getKTGG")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getBZXR() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(sjbService.getBZXR("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getBZXR")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(sjbService.getBZXR("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getBZXR")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getFYGG() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(sjbService.getFYGG("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getFYGG")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(sjbService.getFYGG("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getFYGG")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }


    @Test
    public void getFYPJ() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(sjbService.getFYPJ("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getFYPJ")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(sjbService.getFYPJ("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getFYPJ")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getSXR() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(sjbService.getSXR("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getSXR")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(sjbService.getSXR("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getSXR")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getGQCZ() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(sjbService.getGQCZ("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getGQCZ")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(sjbService.getGQCZ("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getGQCZ")
                .param("name", "南京智享家信息科技有限公司")
                .param("skip", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }


    @Test
    public void getSMRZ() throws Exception {
        /*String str = "{\"name\":\"tom\"}";
        when(sjbService.getSMRZ("宋永昌", "320322198902265610","csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
/*        mvc.perform(MockMvcRequestBuilders.post("/sjb/getSMRZ")
                .param("name", "宋永昌")
                .param("idcaed", "320322198902265610")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));*/

        when(sjbService.getSMRZ("宋永昌", "320322198902265610", "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getSMRZ")
                .param("name", "宋永昌")
                .param("idcard", "320322198902265610")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getSFZSB() throws Exception {
        /*String str = "{\"name\":\"tom\"}";
        when(sjbService.getSMRZ("宋永昌", "320322198902265610","csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
/*        mvc.perform(MockMvcRequestBuilders.post("/sjb/getSMRZ")
                .param("name", "宋永昌")
                .param("idcaed", "320322198902265610")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));*/

        when(sjbService.getSFZSB("320322198902265610", "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getSFZSB")
                .param("imageId", "320322198902265610")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getYHKSB() throws Exception {
        /*String str = "{\"name\":\"tom\"}";
        when(sjbService.getSMRZ("宋永昌", "320322198902265610","csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
/*        mvc.perform(MockMvcRequestBuilders.post("/sjb/getSMRZ")
                .param("name", "宋永昌")
                .param("idcaed", "320322198902265610")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));*/

        when(sjbService.getYHKSB("320322198902265610", "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/sjb/getYHKSB")
                .param("imageId", "320322198902265610")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }


}

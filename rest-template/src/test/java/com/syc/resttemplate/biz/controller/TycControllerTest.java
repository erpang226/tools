package com.syc.resttemplate.biz.controller;

import com.alibaba.fastjson.JSONObject;
import com.syc.resttemplate.biz.service.TycService;
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
public class TycControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private TycService tycService;


    @Test
    public void getEnterpriseBasicInfo() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(tycService.getEnterpriseBasicInfo("南京智享家信息科技有限公司", "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getEnterpriseBasicInfo")
                .param("name", "南京智享家信息科技有限公司")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(tycService.getEnterpriseBasicInfo("南京智享家信息科技有限公司", "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getEnterpriseBasicInfo")
                .param("name", "南京智享家信息科技有限公司")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getDCDY() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(tycService.getDCDY("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getDCDY")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(tycService.getDCDY("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getDCDY")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getXZCF() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(tycService.getXZCF("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getXZCF")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(tycService.getXZCF("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getXZCF")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getJYYC() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(tycService.getJYYC("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getJYYC")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(tycService.getJYYC("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getJYYC")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getKTGG() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(tycService.getKTGG("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getKTGG")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(tycService.getKTGG("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getKTGG")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getBZXR() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(tycService.getBZXR("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getBZXR")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(tycService.getBZXR("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getBZXR")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getFYGG() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(tycService.getFYGG("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getFYGG")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(tycService.getFYGG("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getFYGG")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }


    @Test
    public void getFYPJ() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(tycService.getFYPJ("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getFYPJ")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(tycService.getFYPJ("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getFYPJ")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getSXR() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(tycService.getSXR("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getSXR")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(tycService.getSXR("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getSXR")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }

    @Test
    public void getGQCZ() throws Exception {
        String str = "{\"name\":\"tom\"}";
        when(tycService.getGQCZ("南京智享家信息科技有限公司", 1, "csr2.0")).thenReturn(JSONObject.parseObject(str));
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getGQCZ")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));

        when(tycService.getGQCZ("南京智享家信息科技有限公司", 1, "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getGQCZ")
                .param("name", "南京智享家信息科技有限公司")
                .param("pageNum", "1")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }


    @Test
    public void getSMRZ() throws Exception {
        /*String str = "{\"name\":\"tom\"}";
        when(tycService.getSMRZ("宋永昌", "320322198902265610","csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
/*        mvc.perform(MockMvcRequestBuilders.post("/tyc/getSMRZ")
                .param("name", "宋永昌")
                .param("idcaed", "320322198902265610")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));*/

        when(tycService.getSMRZ("宋永昌", "320322198902265610", "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getSMRZ")
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
        when(tycService.getSMRZ("宋永昌", "320322198902265610","csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
/*        mvc.perform(MockMvcRequestBuilders.post("/tyc/getSMRZ")
                .param("name", "宋永昌")
                .param("idcaed", "320322198902265610")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));*/

        when(tycService.getSFZSB("320322198902265610", "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getSFZSB")
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
        when(tycService.getSMRZ("宋永昌", "320322198902265610","csr2.0")).thenReturn(JSONObject.parseObject(str));

        /*post请求*/
/*        mvc.perform(MockMvcRequestBuilders.post("/tyc/getSMRZ")
                .param("name", "宋永昌")
                .param("idcaed", "320322198902265610")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(str));*/

        when(tycService.getYHKSB("320322198902265610", "csr2.0")).thenThrow(BizException.class);
        /*post请求*/
        mvc.perform(MockMvcRequestBuilders.post("/tyc/getYHKSB")
                .param("imageId", "320322198902265610")
                .param("user", "csr2.0"))
                //请求是否ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                //验证返回结果,如果有的话
                .andExpect(MockMvcResultMatchers.content().string(ReturnEnum.FAIL.errorResult().toJSONString()));

    }


}

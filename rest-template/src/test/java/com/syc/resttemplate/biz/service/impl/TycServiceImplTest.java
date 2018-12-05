package com.syc.resttemplate.biz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.syc.resttemplate.biz.service.TycService;
import com.syc.resttemplate.config.BizException;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TycServiceImplTest {

    @Autowired
    private TycService tycService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private MongoClient mongoClient;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;
    @Value("${third-data.tyc.token}")
    private String tycToken;

    @Value("${third-data.tyc.basic-info.url}")
    private String basicInfoUrl;
    @Value("${spring.data.mongodb.tyc.collection.basic-info}")
    private String basicInfoCollectionName;

    @Value("${third-data.tyc.dcdy.url}")
    private String dcdyUrl;
    @Value("${spring.data.mongodb.tyc.collection.dcdy}")
    private String dcdyCollectionName;

    @Value("${third-data.tyc.xzcf.url}")
    private String xzcfUrl;
    @Value("${spring.data.mongodb.tyc.collection.xzcf}")
    private String xzcfCollectionName;

    @Value("${third-data.tyc.jyyc.url}")
    private String jyycUrl;
    @Value("${spring.data.mongodb.tyc.collection.jyyc}")
    private String jyycCollectionName;

    @Value("${third-data.tyc.ktgg.url}")
    private String ktggUrl;
    @Value("${spring.data.mongodb.tyc.collection.ktgg}")
    private String ktggCollectionName;

    @Value("${third-data.tyc.bzxr.url}")
    private String bzxrUrl;
    @Value("${spring.data.mongodb.tyc.collection.bzxr}")
    private String bzxrCollectionName;

    @Value("${third-data.tyc.fygg.url}")
    private String fyggUrl;
    @Value("${spring.data.mongodb.tyc.collection.fygg}")
    private String fyggCollectionName;

    @Value("${third-data.tyc.fypj.url}")
    private String fypjUrl;
    @Value("${spring.data.mongodb.tyc.collection.fypj}")
    private String fypjCollectionName;

    @Value("${third-data.tyc.sxr.url}")
    private String sxrUrl;
    @Value("${spring.data.mongodb.tyc.collection.sxr}")
    private String sxrCollectionName;

    @Value("${third-data.tyc.smrz.url}")
    private String smrzUrl;
    @Value("${spring.data.mongodb.tyc.collection.smrz}")
    private String smrzCollectionName;

    @Value("${third-data.tyc.gqcz.url}")
    private String gqczUrl;
    @Value("${spring.data.mongodb.tyc.collection.gqcz}")
    private String gqczCollectionName;

    @Value("${third-data.tyc.sfzsb.url}")
    private String sfzsbUrl;
    @Value("${spring.data.mongodb.tyc.collection.sfzsb}")
    private String sfzsbCollectionName;

    @Value("${third-data.tyc.yhksb.url}")
    private String yhksbUrl;
    @Value("${spring.data.mongodb.tyc.collection.yhksb}")
    private String yhksbCollectionName;

    @Test
    public void test() throws BizException {
        System.out.println(".......");
    }

    @Test
    public void getAndSaveBasicInfoFromTYC() throws BizException {
        String name = "南京智享家信息科技有限公司";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", tycToken);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(headers);

        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        when(restTemplate.exchange(basicInfoUrl, HttpMethod.GET, requestEntity, JSONObject.class, name)).thenReturn(responseEntity);

        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(basicInfoCollectionName)).thenReturn(collection);

        JSONObject body = tycService.getEnterpriseBasicInfo(name, "test");
        Assert.assertEquals(target, body);
    }

    @Test
    public void getDCDY() throws BizException {
        String name = "南京智享家信息科技有限公司";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", tycToken);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(headers);

        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        // 不同接口请求
        when(restTemplate.exchange(dcdyUrl, HttpMethod.GET, requestEntity, JSONObject.class, name, 1)).thenReturn(responseEntity);
        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(dcdyCollectionName)).thenReturn(collection);

        JSONObject body = tycService.getDCDY(name, 1, "test");
        Assert.assertEquals(target, body);
    }

    @Test
    public void getXZCF() throws BizException {
        String name = "南京智享家信息科技有限公司";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", tycToken);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(headers);

        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        // 不同接口请求
        when(restTemplate.exchange(xzcfUrl, HttpMethod.GET, requestEntity, JSONObject.class, name, 1)).thenReturn(responseEntity);
        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(xzcfCollectionName)).thenReturn(collection);

        JSONObject body = tycService.getXZCF(name, 1, "test");
        Assert.assertEquals(target, body);
    }

    @Test
    public void getJYYC() throws BizException {
        String name = "南京智享家信息科技有限公司";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", tycToken);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(headers);

        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        // 不同接口请求
        when(restTemplate.exchange(jyycUrl, HttpMethod.GET, requestEntity, JSONObject.class, name, 1)).thenReturn(responseEntity);
        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(jyycCollectionName)).thenReturn(collection);

        JSONObject body = tycService.getJYYC(name, 1, "test");
        Assert.assertEquals(target, body);

    }

    @Test
    public void getKTGG() throws BizException {
        String name = "南京智享家信息科技有限公司";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", tycToken);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(headers);

        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        // 不同接口请求
        when(restTemplate.exchange(ktggUrl, HttpMethod.GET, requestEntity, JSONObject.class, name, 1)).thenReturn(responseEntity);
        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(ktggCollectionName)).thenReturn(collection);

        JSONObject body = tycService.getKTGG(name, 1, "test");
        Assert.assertEquals(target, body);

    }

    @Test
    public void getBZXR() throws BizException {
        String name = "南京智享家信息科技有限公司";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", tycToken);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(headers);

        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        // 不同接口请求
        when(restTemplate.exchange(bzxrUrl, HttpMethod.GET, requestEntity, JSONObject.class, name, 1)).thenReturn(responseEntity);
        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(bzxrCollectionName)).thenReturn(collection);

        JSONObject body = tycService.getBZXR(name, 1, "test");
        Assert.assertEquals(target, body);

    }

    @Test
    public void getFYGG() throws BizException {
        String name = "南京智享家信息科技有限公司";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", tycToken);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(headers);

        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        // 不同接口请求
        when(restTemplate.exchange(fyggUrl, HttpMethod.GET, requestEntity, JSONObject.class, name, 1)).thenReturn(responseEntity);
        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(fyggCollectionName)).thenReturn(collection);

        JSONObject body = tycService.getFYGG(name, 1, "test");
        Assert.assertEquals(target, body);
    }

    @Test
    public void getFYPJ() throws BizException {
        String name = "南京智享家信息科技有限公司";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", tycToken);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(headers);

        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        // 不同接口请求
        when(restTemplate.exchange(fypjUrl, HttpMethod.GET, requestEntity, JSONObject.class, name, 1)).thenReturn(responseEntity);
        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(fypjCollectionName)).thenReturn(collection);

        JSONObject body = tycService.getFYPJ(name, 1, "test");
        Assert.assertEquals(target, body);
    }

    @Test
    public void getSXR() throws BizException {
        String name = "南京智享家信息科技有限公司";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", tycToken);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(headers);

        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        // 不同接口请求
        when(restTemplate.exchange(sxrUrl, HttpMethod.GET, requestEntity, JSONObject.class, name, 1)).thenReturn(responseEntity);
        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(sxrCollectionName)).thenReturn(collection);

        JSONObject body = tycService.getSXR(name, 1, "test");
        Assert.assertEquals(target, body);
    }

    @Test
    public void getGQCZ() throws BizException {
        String name = "南京智享家信息科技有限公司";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", tycToken);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(headers);

        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        // 不同接口请求
        when(restTemplate.exchange(gqczUrl, HttpMethod.GET, requestEntity, JSONObject.class, name, 1)).thenReturn(responseEntity);
        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(gqczCollectionName)).thenReturn(collection);

        JSONObject body = tycService.getGQCZ(name, 1, "test");
        Assert.assertEquals(target, body);
    }


}
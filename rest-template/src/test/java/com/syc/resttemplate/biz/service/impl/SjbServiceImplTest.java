package com.syc.resttemplate.biz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.syc.resttemplate.biz.service.SjbService;
import com.syc.resttemplate.config.BizException;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SjbServiceImplTest {

    private final static String INVOKER = "test";
    @Autowired
    private SjbService sjbService;
    @MockBean
    private RestTemplate restTemplate;
    @Value("${spring.data.mongodb.database}")
    private String databaseName;
    @MockBean
    private MongoClient mongoClient;

    @Value("${third-data.sjb.basic-info.url}")
    private String basicInfoUrl;
    @Value("${third-data.sjb.basic-info.key}")
    private String basicInfoKey;
    @Value("${spring.data.mongodb.sjb.collection.basic-info}")
    private String basicInfoCollectionName;

    @Value("${third-data.sjb.dcdy.url}")
    private String dcdyUrl;
    @Value("${third-data.sjb.dcdy.key}")
    private String dcdyKey;
    @Value("${spring.data.mongodb.sjb.collection.dcdy}")
    private String dcdyCollectionName;

    @Value("${third-data.sjb.xzcf.url}")
    private String xzcfUrl;
    @Value("${third-data.sjb.xzcf.key}")
    private String xzcfKey;
    @Value("${spring.data.mongodb.sjb.collection.xzcf}")
    private String xzcfCollectionName;

    @Value("${third-data.sjb.jyyc.url}")
    private String jyycUrl;
    @Value("${third-data.sjb.jyyc.key}")
    private String jyycKey;
    @Value("${spring.data.mongodb.sjb.collection.jyyc}")
    private String jyycCollectionName;

    @Value("${third-data.sjb.ktgg.url}")
    private String ktggUrl;
    @Value("${third-data.sjb.ktgg.key}")
    private String ktggKey;
    @Value("${spring.data.mongodb.sjb.collection.ktgg}")
    private String ktggCollectionName;

    @Value("${third-data.sjb.bzxr.url}")
    private String bzxrUrl;
    @Value("${third-data.sjb.bzxr.key}")
    private String bzxrKey;
    @Value("${spring.data.mongodb.sjb.collection.bzxr}")
    private String bzxrCollectionName;

    @Value("${third-data.sjb.fygg.url}")
    private String fyggUrl;
    @Value("${third-data.sjb.fygg.key}")
    private String fyggKey;
    @Value("${spring.data.mongodb.sjb.collection.fygg}")
    private String fyggCollectionName;

    @Value("${third-data.sjb.fypj.url}")
    private String fypjUrl;
    @Value("${third-data.sjb.fypj.key}")
    private String fypjKey;
    @Value("${spring.data.mongodb.sjb.collection.fypj}")
    private String fypjCollectionName;

    @Value("${third-data.sjb.sxr.url}")
    private String sxrUrl;
    @Value("${third-data.sjb.sxr.key}")
    private String sxrKey;
    @Value("${spring.data.mongodb.sjb.collection.sxr}")
    private String sxrCollectionName;

    @Value("${third-data.sjb.smrz.url}")
    private String smrzUrl;
    @Value("${third-data.sjb.smrz.key}")
    private String smrzKey;
    @Value("${spring.data.mongodb.sjb.collection.smrz}")
    private String smrzCollectionName;

    @Value("${third-data.sjb.gqcz.url}")
    private String gqczUrl;
    @Value("${third-data.sjb.gqcz.key}")
    private String gqczKey;
    @Value("${spring.data.mongodb.sjb.collection.gqcz}")
    private String gqczCollectionName;

    @Value("${third-data.sjb.sfzsb.url}")
    private String sfzsbUrl;
    @Value("${third-data.sjb.sfzsb.key}")
    private String sfzsbKey;
    @Value("${spring.data.mongodb.sjb.collection.sfzsb}")
    private String sfzsbCollectionName;

    @Value("${third-data.sjb.yhksb.url}")
    private String yhksbUrl;
    @Value("${third-data.sjb.yhksb.key}")
    private String yhksbKey;
    @Value("${spring.data.mongodb.sjb.collection.yhksb}")
    private String yhksbCollectionName;


    @Test
    public void getEnterpriseBasicInfo() throws BizException {

        String name = "南京智享家信息科技有限公司";
        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        when(restTemplate.postForEntity(basicInfoUrl, null, JSONObject.class,
                basicInfoKey, name, "name", "0")).thenReturn(responseEntity);

        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(basicInfoCollectionName)).thenReturn(collection);

        JSONObject body = sjbService.getEnterpriseBasicInfo(name, "name", "0", INVOKER);
        Assert.assertEquals(target, body);
    }

    @Test
    public void getDCDY() throws BizException {

        String name = "南京智享家信息科技有限公司";
        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        when(restTemplate.postForEntity(dcdyUrl, null, JSONObject.class,
                dcdyKey, name, 0)).thenReturn(responseEntity);

        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(dcdyCollectionName)).thenReturn(collection);

        JSONObject body = sjbService.getDCDY(name, 0, INVOKER);
        Assert.assertEquals(target, body);
    }

    @Test
    public void getXZCF() throws BizException {
        String name = "南京智享家信息科技有限公司";
        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        when(restTemplate.postForEntity(xzcfUrl, null, JSONObject.class,
                xzcfKey, name, 0)).thenReturn(responseEntity);

        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(xzcfCollectionName)).thenReturn(collection);

        JSONObject body = sjbService.getXZCF(name, 0, INVOKER);
        Assert.assertEquals(target, body);

    }

    @Test
    public void getJYYC() throws BizException {
        String name = "南京智享家信息科技有限公司";
        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        when(restTemplate.postForEntity(jyycUrl, null, JSONObject.class,
                jyycKey, name, 0)).thenReturn(responseEntity);

        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(jyycCollectionName)).thenReturn(collection);

        JSONObject body = sjbService.getJYYC(name, 0, INVOKER);
        Assert.assertEquals(target, body);
    }

    @Test
    public void getKTGG() throws BizException {
        String name = "南京智享家信息科技有限公司";
        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        when(restTemplate.postForEntity(ktggUrl, null, JSONObject.class,
                ktggKey, name, 0)).thenReturn(responseEntity);

        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(ktggCollectionName)).thenReturn(collection);

        JSONObject body = sjbService.getKTGG(name, 0, INVOKER);
        Assert.assertEquals(target, body);
    }


    @Test
    public void getBZXR() throws BizException {
        String name = "南京智享家信息科技有限公司";
        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        when(restTemplate.postForEntity(bzxrUrl, null, JSONObject.class,
                bzxrKey, name, 0)).thenReturn(responseEntity);

        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(bzxrCollectionName)).thenReturn(collection);

        JSONObject body = sjbService.getBZXR(name, 0, INVOKER);
        Assert.assertEquals(target, body);
    }

    @Test
    public void getFYGG() throws BizException {
        String name = "南京智享家信息科技有限公司";
        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        when(restTemplate.postForEntity(fyggUrl, null, JSONObject.class,
                fyggKey, name, 0)).thenReturn(responseEntity);

        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(fyggCollectionName)).thenReturn(collection);

        JSONObject body = sjbService.getFYGG(name, 0, INVOKER);
        Assert.assertEquals(target, body);
    }

    @Test
    public void getFYPJ() throws BizException {
        String name = "南京智享家信息科技有限公司";
        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        when(restTemplate.postForEntity(fypjUrl, null, JSONObject.class,
                fypjKey, name, 0)).thenReturn(responseEntity);

        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(fypjCollectionName)).thenReturn(collection);

        JSONObject body = sjbService.getFYPJ(name, 0, INVOKER);
        Assert.assertEquals(target, body);
    }

    @Test
    public void getSXR() throws BizException {
        String name = "南京智享家信息科技有限公司";
        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        when(restTemplate.postForEntity(sxrUrl, null, JSONObject.class,
                sxrKey, name, 0)).thenReturn(responseEntity);

        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(sxrCollectionName)).thenReturn(collection);

        JSONObject body = sjbService.getSXR(name, 0, INVOKER);
        Assert.assertEquals(target, body);
    }

    @Test
    public void getSMRZ() throws BizException {
        String name = "南京智享家信息科技有限公司";
        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        when(restTemplate.postForEntity(smrzUrl, null, JSONObject.class,
                smrzKey, name, "32032219890226561x")).thenReturn(responseEntity);

        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(smrzCollectionName)).thenReturn(collection);

        JSONObject body = sjbService.getSMRZ(name, "32032219890226561x", INVOKER);
        Assert.assertEquals(target, body);
    }

    @Test
    public void getGQCZ() throws BizException {
        String name = "南京智享家信息科技有限公司";
        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        when(restTemplate.postForEntity(gqczUrl, null, JSONObject.class,
                gqczKey, name, 0)).thenReturn(responseEntity);

        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(gqczCollectionName)).thenReturn(collection);

        JSONObject body = sjbService.getGQCZ(name, 0, INVOKER);
        Assert.assertEquals(target, body);
    }

    @Test
    public void getSFZSB() throws BizException {
        String name = "a021effe4e664e619fe7ebedfadcb882";
        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        when(restTemplate.postForEntity(sfzsbUrl, null, JSONObject.class,
                sfzsbKey, name)).thenReturn(responseEntity);

        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(sfzsbCollectionName)).thenReturn(collection);

        JSONObject body = sjbService.getSFZSB(name, INVOKER);
        Assert.assertEquals(target, body);
    }

    @Test
    public void getYHKSB() throws BizException {
        String name = "a021effe4e664e619fe7ebedfadcb882";
        String str = "{\"name\":\"tom\"}";
        JSONObject target = JSONObject.parseObject(str);
        //请求返回
        ResponseEntity<JSONObject> responseEntity = new ResponseEntity<>(target, HttpStatus.OK);
        when(restTemplate.postForEntity(yhksbUrl, null, JSONObject.class,
                yhksbKey, name)).thenReturn(responseEntity);

        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        MongoCollection<Document> collection = mock(MongoCollection.class);
        when(mongoClient.getDatabase(databaseName).getCollection(yhksbCollectionName)).thenReturn(collection);

        JSONObject body = sjbService.getYHKSB(name, INVOKER);
        Assert.assertEquals(target, body);

        responseEntity = new ResponseEntity<>(target, HttpStatus.BAD_REQUEST);
        when(restTemplate.postForEntity(yhksbUrl, null, JSONObject.class,
                yhksbKey, name)).thenReturn(responseEntity);

        when(mongoClient.getDatabase(databaseName)).thenReturn(mock(MongoDatabase.class));
        when(mongoClient.getDatabase(databaseName).getCollection(yhksbCollectionName)).thenReturn(collection);

        try {
            sjbService.getYHKSB(name, INVOKER);
        } catch (BizException e) {
            System.out.println(e);
        }

    }


}
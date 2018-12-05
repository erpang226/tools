package com.syc.resttemplate.biz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.syc.resttemplate.biz.constant.ThirdDataConstant;
import com.syc.resttemplate.biz.service.TycService;
import com.syc.resttemplate.config.BizException;
import com.syc.resttemplate.config.ReturnEnum;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Optional;

@Service
public class TycServiceImpl implements TycService {

    @Autowired
    private MongoClient mongoClient;
    @Value("${spring.data.mongodb.database}")
    private String databaseName;
    @Autowired
    private RestTemplate restTemplate;
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


    @Override
    public JSONObject getEnterpriseBasicInfo(String name, String invoker) throws BizException {

        name = name.replaceAll("（", "(").replaceAll("）", ")");
        JSONObject queryCondition = new JSONObject();
        queryCondition.put(ThirdDataConstant.KEY_WORD, name);
        queryCondition.put(ThirdDataConstant.INVOKER, invoker);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", tycToken);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<JSONObject> response = restTemplate.exchange(basicInfoUrl, HttpMethod.GET, requestEntity, JSONObject.class, name);
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new BizException(ReturnEnum.ERROR_100001, queryCondition);
        }

        return insertIntoMongo(basicInfoCollectionName, queryCondition, response);
    }

    @Override
    public JSONObject getDCDY(String name, int pageNum, String invoker) throws BizException {

        name = name.replaceAll("（", "(").replaceAll("）", ")");
        return getJsonObject(name, invoker, dcdyUrl, pageNum, dcdyCollectionName);
    }

    @Override
    public JSONObject getXZCF(String name, int pageNum, String invoker) throws BizException {

        name = name.replaceAll("（", "(").replaceAll("）", ")");
        return getJsonObject(name, invoker, xzcfUrl, pageNum, xzcfCollectionName);
    }

    @Override
    public JSONObject getJYYC(String name, int pageNum, String invoker) throws BizException {
        name = name.replaceAll("（", "(").replaceAll("）", ")");
        return getJsonObject(name, invoker, jyycUrl, pageNum, jyycCollectionName);
    }

    @Override
    public JSONObject getKTGG(String name, int pageNum, String invoker) throws BizException {
        name = name.replaceAll("（", "(").replaceAll("）", ")");
        return getJsonObject(name, invoker, ktggUrl, pageNum, ktggCollectionName);
    }

    @Override
    public JSONObject getBZXR(String name, int pageNum, String invoker) throws BizException {
        name = name.replaceAll("（", "(").replaceAll("）", ")");
        return getJsonObject(name, invoker, bzxrUrl, pageNum, bzxrCollectionName);
    }

    @Override
    public JSONObject getFYGG(String name, int pageNum, String invoker) throws BizException {
        name = name.replaceAll("（", "(").replaceAll("）", ")");
        return getJsonObject(name, invoker, fyggUrl, pageNum, fyggCollectionName);
    }

    @Override
    public JSONObject getFYPJ(String name, int pageNum, String invoker) throws BizException {
        name = name.replaceAll("（", "(").replaceAll("）", ")");
        return getJsonObject(name, invoker, fypjUrl, pageNum, fypjCollectionName);
    }

    @Override
    public JSONObject getSXR(String name, int pageNum, String invoker) throws BizException {
        name = name.replaceAll("（", "(").replaceAll("）", ")");
        return getJsonObject(name, invoker, sxrUrl, pageNum, sxrCollectionName);
    }

    @Override
    public JSONObject getSMRZ(String name, String idcard, String invoker) throws BizException {
        throw new BizException(ReturnEnum.FAIL);
    }

    @Override
    public JSONObject getGQCZ(String name, int pageNum, String invoker) throws BizException {
        name = name.replaceAll("（", "(").replaceAll("）", ")");
        return getJsonObject(name, invoker, gqczUrl, pageNum, gqczCollectionName);
    }

    @Override
    public JSONObject getSFZSB(String imageId, String invoker) throws BizException {
        throw new BizException(ReturnEnum.FAIL);
    }

    @Override
    public JSONObject getYHKSB(String imageId, String invoker) throws BizException {
        throw new BizException(ReturnEnum.FAIL);
    }

    /**
     * 分页查询
     *
     * @param name           企业名称
     * @param invoker        调用者
     * @param url            url
     * @param pageNum        第几页
     * @param collectionName 集合名称
     * @return
     * @throws BizException
     */
    private JSONObject getJsonObject(String name, String invoker, String url, int pageNum, String collectionName) throws BizException {
        JSONObject queryCondition = new JSONObject();
        queryCondition.put(ThirdDataConstant.KEY_WORD, name);
        queryCondition.put(ThirdDataConstant.INVOKER, invoker);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", tycToken);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<JSONObject> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, JSONObject.class, name, pageNum);
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new BizException(ReturnEnum.ERROR_100001, queryCondition);
        }

        return insertIntoMongo(collectionName, queryCondition, response);
    }

    private JSONObject insertIntoMongo(String collectionName, JSONObject queryCondition, ResponseEntity<JSONObject> response) {
        JSONObject body = Optional.ofNullable(response.getBody()).orElse(new JSONObject());
        body.put(ThirdDataConstant.CREATE_TIME, new Date().getTime());
        body.put(ThirdDataConstant.QUERY_CONDITION, queryCondition);

        // 保存操作
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(collectionName);
        Document data = Document.parse(body.toJSONString());
        basicInfoCollection.insertOne(data);
        return body;
    }
}

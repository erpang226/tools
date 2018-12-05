package com.syc.resttemplate.biz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.syc.resttemplate.biz.constant.ThirdDataConstant;
import com.syc.resttemplate.biz.service.SjbService;
import com.syc.resttemplate.config.BizException;
import com.syc.resttemplate.config.ReturnEnum;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

@Service
public class SjbServiceImpl implements SjbService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SjbServiceImpl.class);


    @Autowired
    private RestTemplate restTemplate;
    @Value("${spring.data.mongodb.database}")
    private String databaseName;
    @Autowired
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


    @Override
    public JSONObject getEnterpriseBasicInfo(String keyWord, String type, String entType, String invoker) throws BizException {

        if ("name".equals(type)) {
            keyWord = keyWord.replaceAll("（", "(").replaceAll("）", ")");
        }
        JSONObject queryCondition = new JSONObject();
        queryCondition.put(ThirdDataConstant.KEY_WORD, keyWord);
        queryCondition.put("type", type);
        queryCondition.put("entType", entType);
        queryCondition.put(ThirdDataConstant.INVOKER, invoker);

        ResponseEntity<JSONObject> response = restTemplate.postForEntity(basicInfoUrl, null, JSONObject.class,
                basicInfoKey, keyWord, type, entType);
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new BizException(ReturnEnum.ERROR_100001, queryCondition);
        }

        JSONObject body = Optional.ofNullable(response.getBody()).orElse(new JSONObject());
        body.put(ThirdDataConstant.CREATE_TIME, new Date().getTime());
        body.put(ThirdDataConstant.QUERY_CONDITION, queryCondition);

        // 保存操作
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(basicInfoCollectionName);
        Document data = Document.parse(body.toJSONString());
        basicInfoCollection.insertOne(data);
        return body;
    }

    @Override
    public JSONObject getDCDY(String name, int skip, String invoker) throws BizException {
        name = name.replaceAll("（", "(").replaceAll("）", ")");

        return queryPage(dcdyUrl, dcdyKey, name, skip, dcdyCollectionName, invoker);

    }

    @Override
    public JSONObject getXZCF(String name, int skip, String invoker) throws BizException {
        name = name.replaceAll("（", "(").replaceAll("）", ")");

        return queryPage(xzcfUrl, xzcfKey, name, skip, xzcfCollectionName, invoker);
    }

    @Override
    public JSONObject getJYYC(String name, int skip, String invoker) throws BizException {
        name = name.replaceAll("（", "(").replaceAll("）", ")");
        return queryPage(jyycUrl, jyycKey, name, skip, jyycCollectionName, invoker);
    }

    @Override
    public JSONObject getKTGG(String name, int skip, String invoker) throws BizException {
        name = name.replaceAll("（", "(").replaceAll("）", ")");
        return queryPage(ktggUrl, ktggKey, name, skip, ktggCollectionName, invoker);
    }

    @Override
    public JSONObject getBZXR(String name, int skip, String invoker) throws BizException {
        name = name.replaceAll("（", "(").replaceAll("）", ")");
        return queryPage(bzxrUrl, bzxrKey, name, skip, bzxrCollectionName, invoker);
    }

    @Override
    public JSONObject getFYGG(String name, int skip, String invoker) throws BizException {
        name = name.replaceAll("（", "(").replaceAll("）", ")");
        return queryPage(fyggUrl, fyggKey, name, skip, fyggCollectionName, invoker);
    }

    @Override
    public JSONObject getFYPJ(String name, int skip, String invoker) throws BizException {
        name = name.replaceAll("（", "(").replaceAll("）", ")");
        return queryPage(fypjUrl, fypjKey, name, skip, fypjCollectionName, invoker);
    }

    @Override
    public JSONObject getSXR(String keyWord, int skip, String invoker) throws BizException {
        keyWord = keyWord.replaceAll("（", "(").replaceAll("）", ")");
        return queryPage(sxrUrl, sxrKey, keyWord, skip, sxrCollectionName, invoker);
    }

    @Override
    public JSONObject getSMRZ(String name, String idcard, String invoker) throws BizException {
        LOGGER.info("实名认证查询链接：{}", smrzUrl);
        ResponseEntity<JSONObject> response = restTemplate.postForEntity(smrzUrl, null, JSONObject.class,
                smrzKey, name, idcard);
        JSONObject queryCondition = new JSONObject();
        queryCondition.put("name", name);
        queryCondition.put("idcard", idcard);
        queryCondition.put(ThirdDataConstant.INVOKER, invoker);
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new BizException(ReturnEnum.ERROR_100001, queryCondition);
        }

        JSONObject body = Optional.ofNullable(response.getBody()).orElse(new JSONObject());
        body.put(ThirdDataConstant.CREATE_TIME, new Date().getTime());
        body.put(ThirdDataConstant.QUERY_CONDITION, queryCondition);
        // 保存操作
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(smrzCollectionName);
        Document data = Document.parse(body.toJSONString());
        basicInfoCollection.insertOne(data);
        return body;
    }

    @Override
    public JSONObject getGQCZ(String keyWord, int skip, String invoker) throws BizException {
        keyWord = keyWord.replaceAll("（", "(").replaceAll("）", ")");
        return queryPage(gqczUrl, gqczKey, keyWord, skip, gqczCollectionName, invoker);
    }

    @Override
    public JSONObject getSFZSB(String imageId, String invoker) throws BizException {
        LOGGER.info("身份证识别链接：{}", sfzsbUrl);
        ResponseEntity<JSONObject> response = restTemplate.postForEntity(sfzsbUrl, null, JSONObject.class,
                sfzsbKey, imageId);
        JSONObject queryCondition = new JSONObject();
        queryCondition.put("imageId", imageId);
        queryCondition.put(ThirdDataConstant.INVOKER, invoker);
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new BizException(ReturnEnum.ERROR_100001, queryCondition);
        }

        JSONObject body = Optional.ofNullable(response.getBody()).orElse(new JSONObject());
        body.put(ThirdDataConstant.CREATE_TIME, new Date().getTime());
        body.put(ThirdDataConstant.QUERY_CONDITION, queryCondition);
        // 保存操作
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(sfzsbCollectionName);
        Document data = Document.parse(body.toJSONString());
        basicInfoCollection.insertOne(data);
        return body;
    }

    @Override
    public JSONObject getYHKSB(String imageId, String invoker) throws BizException {
        LOGGER.info("银行卡识别链接：{}", yhksbUrl);
        ResponseEntity<JSONObject> response = restTemplate.postForEntity(yhksbUrl, null, JSONObject.class,
                yhksbKey, imageId);
        JSONObject queryCondition = new JSONObject();
        queryCondition.put("imageId", imageId);
        queryCondition.put(ThirdDataConstant.INVOKER, invoker);
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new BizException(ReturnEnum.ERROR_100001, queryCondition);
        }

        JSONObject body = Optional.ofNullable(response.getBody()).orElse(new JSONObject());
        body.put(ThirdDataConstant.CREATE_TIME, new Date().getTime());
        body.put(ThirdDataConstant.QUERY_CONDITION, queryCondition);
        // 保存操作
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(yhksbCollectionName);
        Document data = Document.parse(body.toJSONString());
        basicInfoCollection.insertOne(data);
        return body;
    }

    /**
     * @param url            url
     * @param key            key
     * @param keyWord        企业名称、纳税人识别号等
     * @param skip           跳过条目数
     * @param collectionName 集合名称
     * @return
     * @throws BizException
     */
    private JSONObject queryPage(String url, String key, String keyWord, int skip, String collectionName, String invoker) throws BizException {
        LOGGER.info("分页查询链接：{}", url);
        ResponseEntity<JSONObject> response = restTemplate.postForEntity(url, null, JSONObject.class,
                key, keyWord, skip);

        JSONObject queryCondition = new JSONObject();
        queryCondition.put(ThirdDataConstant.KEY_WORD, keyWord);
        queryCondition.put("skip", skip);
        queryCondition.put(ThirdDataConstant.INVOKER, invoker);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new BizException(ReturnEnum.ERROR_100001, queryCondition);
        }

        JSONObject body = Optional.ofNullable(response.getBody()).orElse(new JSONObject());
        body.put(ThirdDataConstant.CREATE_TIME, new Date().getTime());
        body.put(ThirdDataConstant.QUERY_CONDITION, queryCondition);
        // 保存操作
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(collectionName);
        Document data = Document.parse(body.toJSONString());
        basicInfoCollection.insertOne(data);
        return body;
    }


    @Override
    public List<JSONObject> queryEnterpriseBasicInfo(String name, int limit, String invoker) {
        LOGGER.info("[{}]从数据库查询企业基本信息.", invoker);
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(basicInfoCollectionName);
        FindIterable<Document> findIterable = basicInfoCollection.find(eq(ThirdDataConstant.QUERY_CONDITION + "." + ThirdDataConstant.KEY_WORD, name))
                .sort(new Document().append(ThirdDataConstant.CREATE_TIME, -1))
                .limit(limit);
        return getJsonObjects(findIterable);
    }

    @Override
    public List<JSONObject> queryDCDY(String name, int limit, String invoker) {
        LOGGER.info("[{}]从数据库查询动产抵押.", invoker);
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(dcdyCollectionName);
        FindIterable<Document> findIterable = basicInfoCollection.find(eq(ThirdDataConstant.QUERY_CONDITION + "." + ThirdDataConstant.KEY_WORD, name))
                .sort(new Document().append(ThirdDataConstant.CREATE_TIME, -1))
                .limit(limit);
        return getJsonObjects(findIterable);
    }

    @Override
    public List<JSONObject> queryXZCF(String name, int limit, String invoker) {
        LOGGER.info("[{}]从数据库查询行政处罚.", invoker);
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(xzcfCollectionName);
        FindIterable<Document> findIterable = basicInfoCollection.find(eq(ThirdDataConstant.QUERY_CONDITION + "." + ThirdDataConstant.KEY_WORD, name))
                .sort(new Document().append(ThirdDataConstant.CREATE_TIME, -1))
                .limit(limit);
        return getJsonObjects(findIterable);
    }

    @Override
    public List<JSONObject> queryJYYC(String name, int limit, String invoker) {
        LOGGER.info("[{}]从数据库查询经营异常.", invoker);
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(jyycCollectionName);
        FindIterable<Document> findIterable = basicInfoCollection.find(eq(ThirdDataConstant.QUERY_CONDITION + "." + ThirdDataConstant.KEY_WORD, name))
                .sort(new Document().append(ThirdDataConstant.CREATE_TIME, -1))
                .limit(limit);
        return getJsonObjects(findIterable);
    }

    @Override
    public List<JSONObject> queryKTGG(String name, int limit, String invoker) {
        LOGGER.info("[{}]从数据库查询开庭公告.", invoker);
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(ktggCollectionName);
        FindIterable<Document> findIterable = basicInfoCollection.find(eq(ThirdDataConstant.QUERY_CONDITION + "." + ThirdDataConstant.KEY_WORD, name))
                .sort(new Document().append(ThirdDataConstant.CREATE_TIME, -1))
                .limit(limit);
        return getJsonObjects(findIterable);
    }

    @Override
    public List<JSONObject> queryBZXR(String name, int limit, String invoker) {
        LOGGER.info("[{}]从数据库查询被执行人.", invoker);
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(bzxrCollectionName);
        FindIterable<Document> findIterable = basicInfoCollection.find(eq(ThirdDataConstant.QUERY_CONDITION + "." + ThirdDataConstant.KEY_WORD, name))
                .sort(new Document().append(ThirdDataConstant.CREATE_TIME, -1))
                .limit(limit);
        return getJsonObjects(findIterable);
    }

    @Override
    public List<JSONObject> queryFYGG(String name, int limit, String invoker) {
        LOGGER.info("[{}]从数据库查询法院公告.", invoker);
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(fyggCollectionName);
        FindIterable<Document> findIterable = basicInfoCollection.find(eq(ThirdDataConstant.QUERY_CONDITION + "." + ThirdDataConstant.KEY_WORD, name))
                .sort(new Document().append(ThirdDataConstant.CREATE_TIME, -1))
                .limit(limit);
        return getJsonObjects(findIterable);
    }

    @Override
    public List<JSONObject> queryFYPJ(String name, int limit, String invoker) {
        LOGGER.info("[{}]从数据库查询法院判决.", invoker);
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(fypjCollectionName);
        FindIterable<Document> findIterable = basicInfoCollection.find(eq(ThirdDataConstant.QUERY_CONDITION + "." + ThirdDataConstant.KEY_WORD, name))
                .sort(new Document().append(ThirdDataConstant.CREATE_TIME, -1))
                .limit(limit);
        return getJsonObjects(findIterable);
    }

    @Override
    public List<JSONObject> querySXR(String keyword, int limit, String invoker) {
        LOGGER.info("[{}]从数据库查询失信人.", invoker);
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(sxrCollectionName);
        FindIterable<Document> findIterable = basicInfoCollection.find(eq(ThirdDataConstant.QUERY_CONDITION + "." + ThirdDataConstant.KEY_WORD, keyword))
                .sort(new Document().append(ThirdDataConstant.CREATE_TIME, -1))
                .limit(limit);
        return getJsonObjects(findIterable);
    }

    @Override
    public List<JSONObject> querySMRZ(String name, String idcard, int limit, String invoker) {
        LOGGER.info("[{}]从数据库查询实名认证.", invoker);
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(smrzCollectionName);
        FindIterable<Document> findIterable = basicInfoCollection
                .find(and(eq("queryCondition.name", name), eq("queryCondition.idcard", idcard)))
                .sort(new Document().append(ThirdDataConstant.CREATE_TIME, -1))
                .limit(limit);
        return getJsonObjects(findIterable);
    }

    @Override
    public List<JSONObject> queryGQCZ(String keyword, int limit, String invoker) {
        LOGGER.info("[{}]从数据库查询股权出质.", invoker);
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(gqczCollectionName);
        FindIterable<Document> findIterable = basicInfoCollection.find(eq(ThirdDataConstant.QUERY_CONDITION + "." + ThirdDataConstant.KEY_WORD, keyword))
                .sort(new Document().append(ThirdDataConstant.CREATE_TIME, -1))
                .limit(limit);
        return getJsonObjects(findIterable);
    }

    @Override
    public List<JSONObject> querySFZSB(String imageId, int limit, String invoker) {
        LOGGER.info("[{}]从数据库查询身份证识别.", invoker);
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(sfzsbCollectionName);
        FindIterable<Document> findIterable = basicInfoCollection.find(eq("queryCondition.imageId", imageId))
                .sort(new Document().append(ThirdDataConstant.CREATE_TIME, -1))
                .limit(limit);
        return getJsonObjects(findIterable);
    }

    @Override
    public List<JSONObject> queryYHKSB(String imageId, int limit, String invoker) {
        LOGGER.info("[{}]从数据库查询银行卡识别.", invoker);
        MongoCollection<Document> basicInfoCollection = mongoClient.getDatabase(databaseName).getCollection(yhksbCollectionName);
        FindIterable<Document> findIterable = basicInfoCollection.find(eq("queryCondition.imageId", imageId))
                .sort(new Document().append(ThirdDataConstant.CREATE_TIME, -1))
                .limit(limit);
        return getJsonObjects(findIterable);
    }

    /**
     * FindIterable<Document> --> List<JSONObject>
     *
     * @param findIterable
     * @return
     */
    private List<JSONObject> getJsonObjects(FindIterable<Document> findIterable) {
        List<JSONObject> list = new ArrayList<>();
        for (Document d : findIterable) {
            list.add(JSONObject.parseObject(d.toJson()));
        }
        return list;
    }
}

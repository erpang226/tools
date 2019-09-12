package com.syc.kydata.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.syc.kydata.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/8/30
 * author: song yong chang
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private static final String API_KEY = "b2934678d6304059bb57528994c3e4cb";
    private static final String API_SECRET = "9a7e8777770e4ed98ae1279d60abaa73";

    @Override
    public void register() throws NoSuchAlgorithmException, InvalidKeyException {

        String timestamp = String.valueOf(System.currentTimeMillis());
        String sign = createSign(timestamp,API_SECRET);

        JSONObject param=new JSONObject();
        param.put("user_token","1234");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        header.add("X-AK-KEY", API_KEY);
        header.add("X-AK-PIN", sign);
        header.add("X-AK-TS", timestamp);
        HttpEntity<JSONObject> httpEntity = new HttpEntity<>(param, header);

        System.out.println(httpEntity);

        JSONObject response = restTemplate.postForObject("https://biz.lixiaoskb.com/services/v2/rest/user/register",
                httpEntity, JSONObject.class);

        System.out.println(response);

    }

    private String createSign(String timestamp, String apiSecret) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(apiSecret.getBytes(), HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        return Base64.getEncoder().encodeToString(mac.doFinal(timestamp.getBytes()));
    }

    @Override
    public boolean checkSign(String timestamp, String apiSecret, String sign) {
        String temp;
        try {
            temp=createSign(timestamp,apiSecret);
            System.out.println(temp);
        } catch (NoSuchAlgorithmException e) {
            return false;
        } catch (InvalidKeyException e) {
            System.out.println("Invalid Key Exception");
            return false;
        }
        return temp.equals(sign);
    }
}

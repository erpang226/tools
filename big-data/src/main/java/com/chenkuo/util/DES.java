package com.chenkuo.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * DES 算法 1972美国IBM研制，对称加密算法
 *
 * @author stone
 * @date 2014-03-10 04:47:05
 */
public class DES {
    // 算法名称
    public static final String KEY_ALGORITHM = "DES";
    // 算法名称/加密模式/填充方式
    public static final String CIPHER_ALGORITHM_ECB = "DES/ECB/PKCS5Padding";
    public static final String CIPHER_ALGORITHM_CBC = "DES/CBC/PKCS5Padding";

    public static void main(String[] args) throws Exception {

        //1.http post 请求



        //2.解密



        String response = "Ndbr8I/zP5YN8UBDxysMe+Olfs6s0amjl/n6Yw4V48ozg/uWT8lJDVE1qrECFwddDcz127CPNp+nS7C746328gfzbB14Md0maE1l8G8LB12WDqzRoUub8ndJ5QeXeoJe8FeyHWRXfvyFt8bV+ME3rdVM2zkN/A9oqj2YUX2NSuafw66b8Y+Kq6E2yZR1jT+K5OkPxZ6wTI29/q1sIZKT8/50AEGnpsPgUyAGx4Uuo+uV3NFBIDnTRO/bxxvJFbm8vNIMZtsXrItU3YNqD70vbtMQ+Nh0ISHrMo3nOYSzCmWmoIGrkcrSzf/DMtwT14xS3nmwhiWHJESXjyOX3n2OCTZHt6fFobEd85bhZGf4ZI14gjSGI5Fy0KvUXjgyB+OmF9et+xiiWAJ3OyJ1IGP2UwybB8GcXEzQoOXgLueACKI2tAvWalin+TfadPifKjVtEsWE3j8ItiNKmsVwROcoCjVWPPdM95hBJbma/kxALj8qR93f0whKY1srsHZc4mW0unS2AY3jccehgA4qMutU5m2CpZpZZ+KKu45jY4hNs1mW4Ckk6rIdY75WSknSTJHqqsplNl5bxFdeeWOeAL1VmmtB/vnv6XrUOXdGG0twue5Ho7MDcB2TsqDZ7uP/TvflF39v3Ig59yJZLuenYG1tVtUXDbgTefIjOBJ8+wqbS5qvq8Og8/S/TS5CX3AhWa8hBEzYnLMLoSULFZr7bdz+3gybiwd1AOIyByl5jnIoZV0=";
        System.out.println("len: "+response.length());
        //解密
        String result = new String(decrypt_cbc(Base64.decodeBase64(response), "i-xinnuo".getBytes("utf-8")), "UTF-8");

        System.out.println("解密结果： "+result);

    }

    //	@Test
    public void testDes() {
        try {
            String result = "爱信诺";
            String key1 = "i-xinnuo";
            String result1 = Base64.encodeBase64String(DES.encrypt_cbc(result.getBytes("utf-8"), key1.getBytes("utf-8")));
            System.out.println(result1);
            String password1 = new String(DES.decrypt_cbc(Base64.decodeBase64(result1), key1.getBytes("utf-8")), "UTF-8");
            System.out.println(password1);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static byte[] getIV() {
        String iv = "12345678"; // IV length: must be 8 bytes long
        return iv.getBytes();
    }

    /**
     * 生成密钥
     *
     * @return
     * @throws Exception
     */
    public static byte[] generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(56); // des 必须是56, 此初始方法不必须调用
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * 还原密钥
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static Key toKey(byte[] key) throws Exception {
        DESKeySpec des = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(des);
        return secretKey;
    }

    /**
     * 加密
     *
     * @param data 原文
     * @param key
     * @return 密文
     * @throws Exception
     */
    public static byte[] encrypt_cbc(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
        cipher.init(Cipher.ENCRYPT_MODE, k, new IvParameterSpec(getIV()));
        return cipher.doFinal(data);
    }

    /**
     * 加密
     *
     * @param data 原文
     * @param key
     * @return 密文
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
        cipher.init(Cipher.ENCRYPT_MODE, k, new SecureRandom());
        return cipher.doFinal(data);
    }

    /**
     * 解密
     *
     * @param data 密文
     * @param key
     * @return 明文、原文
     * @throws Exception
     */
    public static byte[] decrypt_cbc(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
        cipher.init(Cipher.DECRYPT_MODE, k, new IvParameterSpec(getIV()));
        return cipher.doFinal(data);
    }

    /**
     * 解密
     *
     * @param data 密文
     * @param key
     * @return 明文、原文
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
        cipher.init(Cipher.DECRYPT_MODE, k, new SecureRandom());
        return cipher.doFinal(data);
    }


}

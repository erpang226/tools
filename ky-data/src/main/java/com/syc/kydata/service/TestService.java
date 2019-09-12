package com.syc.kydata.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/8/30
 * author: song yong chang
 */
public interface TestService {

    void register() throws NoSuchAlgorithmException, InvalidKeyException;

    boolean checkSign(String timestamp, String apiSecret, String sign);
}

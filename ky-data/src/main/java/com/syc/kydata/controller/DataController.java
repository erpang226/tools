package com.syc.kydata.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.syc.kydata.dto.ReturnData;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/9/2
 * author: song yong chang
 */
@RestController
@RequestMapping("/data")
public class DataController {

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @GetMapping(value = "/register")
    public String test() {
        logger.info("register api...");

        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 用户注册接口回调
     *
     * @param user_token
     * @return
     */
    @PostMapping(value = "/registerCallback")
    public ReturnData registerCallback(String user_token) {
        logger.info("registerCallback api...");
        logger.info("user_token: {}", user_token);

        ReturnData data = ReturnData.success();
        if ("1234-token".equals(user_token)) {
            JSONObject json = new JSONObject();
            json.put("uid", "1234");
            json.put("username", "syc");
            json.put("role", "mainaccount");
            json.put("company_name", "南京硅基智能科技有限公司");
            json.put("email", "398399363@qq.com");
            // 固定写死
            json.put("mobile", JSON.parse("[\"5d6e300ad4c2f801b6230816\"]"));
            json.put("plugin", "18951078236");
            json.put("expiry_time", System.currentTimeMillis() + 1000 * 60 * 60 * 4);// 4小时
            json.put("meals", JSON.parse("[{\"type\" : \"试用\",\"total_amount\":10000}]"));
            json.put("extra", JSON.parse("{\"enableCall\":true,\"enableTask\":true,\"taskQuota\":10000}"));

            data.setData(json);
        }

        logger.info("return data: {}", data);
        return data;
    }


    /**
     * 任务中心(集成系统)回调
     *
     * @param request
     * @return
     * @throws IOException
     */
    @PutMapping(value = "/taskCallback")
    public JSONObject taskCallback(HttpServletRequest request) throws IOException {
        logger.info("taskCallback api...");
        String bodyStr = IOUtils.toString(request.getReader());
        JSONObject body = JSON.parseObject(bodyStr, JSONObject.class);
        logger.info("request body: {}", body);

        JSONObject returnDta = new JSONObject();
        try {
            Integer userId=body.getInteger("userId");
            logger.info("userId{}",userId);
            JSONArray customers = body.getJSONArray("customers");
            customers.toJavaList(JSONObject.class).forEach(c -> {
                logger.info("customer:{}",c);

            });
        }catch (Exception e){
            logger.error("api批量导入数据数据失败",e);
            returnDta.put("code", 1000);
            returnDta.put("message", "导入数据数据失败");
        }

        returnDta.put("code", 0);
        returnDta.put("message", "");
        return returnDta;
    }


}

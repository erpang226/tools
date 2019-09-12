package com.syc.kydata.controller;

import com.syc.kydata.utils.ValidateCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/9/3
 * author: song yong chang
 */
@RestController
@RequestMapping("/login")
public class ValidateCodeController {
    private static final Logger logger = LoggerFactory.getLogger(ValidateCodeController.class);

    @Autowired
    private ValidateCodeUtil validateCodeUtil;

    /**
     * 生成验证码
     */
    @GetMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = request.getSession().getId();
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            validateCodeUtil.getRandomCode(sessionId, response);//输出验证码图片方法
        } catch (Exception e) {
            logger.error("获取验证码失败>>>>   ", e);
        }
    }

    /**
     * 校验验证码
     */
    @PostMapping(value = "/checkVerify", headers = "Accept=application/json")
    public boolean checkVerify(@RequestParam String verifyInput, HttpSession session) {
        try {
            String sessionId = session.getId();
//            微服务环境下,random code 存入redis中
            String random = validateCodeUtil.getValidateCodeFromRedis(sessionId);
            if (random == null || verifyInput == null) {
                return false;
            }
            return random.equalsIgnoreCase(verifyInput);
        } catch (Exception e) {
            logger.error("验证码校验失败", e);
            return false;
        }
    }

}
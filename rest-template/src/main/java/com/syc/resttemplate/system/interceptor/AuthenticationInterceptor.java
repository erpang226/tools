package com.syc.resttemplate.system.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author syc
 * @ClassName: AuthenticationInterceptor
 * @Description: 拦截器
 * @date 2018年1月11日
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("AuthenticationInterceptor begin ... ");
        String token = request.getParameter("token");
        logger.info("token is [{}]", token);
        logger.info("client name is [{}]", request.getRemoteHost());
        logger.info("client address is [{}]", request.getRemoteAddr());
        logger.info("client request url is [{}]", request.getRequestURL());

        request.setAttribute("user", "csr2.0");

        return true;
    }


}

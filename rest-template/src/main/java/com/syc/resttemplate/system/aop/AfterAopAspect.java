package com.syc.resttemplate.system.aop;

import com.syc.resttemplate.config.BizException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AfterAopAspect {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @After(value = "execution(* com.chenkuo.enterpriselib.biz.service.*.*(..))")
    public void after(JoinPoint joinPoint) {
        logger.info("after execution of {}", joinPoint);
    }

    @AfterReturning(value = "execution(* com.chenkuo.enterpriselib.biz.service.*.*(..))",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("{} returned with value {}", joinPoint, result);
    }

    @AfterThrowing(value = "execution(* com.chenkuo.enterpriselib.biz.service.*.*(..))", throwing = "e")
    public void afterThrowing(Throwable e) {
        // logging
        if (e instanceof BizException) {
            BizException be = (BizException) e;
            logger.info("Third Data Interface BizException: {}", be.getReturnObj());
            logger.info("BizException: ", be);
        } else {
            logger.info("exception error: ", e);
        }
        // other things

    }

}

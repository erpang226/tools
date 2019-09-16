package com.hiynn.advice;

import com.alibaba.fastjson.JSON;
import com.hiynn.dictionary.Result;
import com.hiynn.dictionary.ReturnEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerAdviceHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String errorHandler(Exception ex) {
        if (ex instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return JSON.toJSONString(Result.getSuccessResult(ReturnEnum.FAIL));
        } else {
            return JSON.toJSONString(Result.getSuccessResult(ReturnEnum.ERROR_000001));
        }
    }

}

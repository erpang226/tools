package com.syc.resttemplate.config;


import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * 业务异常定义
 *
 * @author syc
 * @version [版本号, 2018/2/8]
 * @see [可以参考 ]
 */
public class BizException extends InvocationTargetException implements Serializable {

    private static final long serialVersionUID = -5466180152813164957L;

    private final ReturnEnum returnEnum;

    private Object returnObj;

    public BizException(ReturnEnum returnEnum, Throwable throwable) {
        super(throwable, returnEnum.getMsg());
        this.returnEnum = returnEnum;
    }

    public BizException(ReturnEnum returnEnum) {
        this.returnEnum = returnEnum;
    }

    public BizException(ReturnEnum returnEnum, Object returnObj) {
        this.returnEnum = returnEnum;
        this.returnObj = returnObj;
    }

    public BizException(ReturnEnum returnEnum, Object returnObj, Throwable throwable) {
        super(throwable, returnEnum.getMsg());
        this.returnEnum = returnEnum;
        this.returnObj = returnObj;
    }

    public ReturnEnum getReturnEnum() {
        return returnEnum;
    }

    public Object getReturnObj() {
        return returnObj;
    }

    public void setReturnObj(Object returnObj) {
        this.returnObj = returnObj;
    }
}

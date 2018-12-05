package com.syc.resttemplate.config;

import com.alibaba.fastjson.JSONObject;

/**
 * 返回错误信息
 *
 * @author syc
 * @version [版本号, 2018/1/29]
 * @see [可以参考 ]
 */
public enum ReturnEnum {

    /**
     * 成功
     */
    SUCCESS("000000", "success"),

    /**
     * 失败
     */
    FAIL("111111", "请求失败"),

    /**
     * 系统错误
     */
    ERROR_000001("000001", "system error"),
    /**
     * 参数校验失败
     */
    ERROR_000002("000002", "Param Validate Error"),
    /**
     * 没有该方法
     */
    ERROR_000003("000003", "No Such Method Exception"),
    /**
     * 非法访问
     */
    ERROR_000004("000004", "Illegal Access Exception"),
    /**
     * 调用目标异常
     */
    ERROR_000005("000005", "Invocation Target Exception"),
    /**
     * 获取输入流失败
     */
    ERROR_000006("000006", "Get InputStream Failed"),
    /**
     * 不被支持的token
     */
    ERROR_000007("000007", "Unsupported Jwt Exception"),
    /**
     * 过期的token
     */
    ERROR_000008("000008", "Expired Jwt Exception"),
    /**
     * token 结构被破坏
     */
    ERROR_000009("000009", "Malformed Jwt Exception"),
    /**
     * token 签名异常
     */
    ERROR_000010("000010", "Signature Exception"),
    /**
     * token 参数非法
     */
    ERROR_000011("000011", "Token Illegal Argument Exception"),
    /**
     * 过期的refresh token
     */
    ERROR_000012("000012", "Expired Refresh-Token Exception"),

    /**
     * 权限校验未通过
     */
    ERROR_000013("000013", "权限校验未通过"),

    /**
     * 类型转换异常
     */
    ERROR_000014("000014", "类型转换异常"),

    /**
     * 业务 token 过期
     */
    ERROR_000015("000015", "Expired Biz Jwt Exception"),

    /******************数据宝***********************/
    ERROR_100001("100001", "数据宝企业基础信息校验接口调用失败，name：[{}]")
    /******************数据宝***********************/
    ;


    /**
     * 返回码
     */
    private String code;
    /**
     * 返回信息
     */
    private String msg;

    ReturnEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ReturnEnum getReturnEnum(String code) {
        for (ReturnEnum returnEnum : ReturnEnum.values()) {
            if (returnEnum.getCode().equals(code)) {
                return returnEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public JSONObject errorResult() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("returnCode", this.getCode());
        jsonObject.put("returnMessage", this.getMsg());
        jsonObject.put("returnData", "");
        return jsonObject;
    }
}

package com.guiji.training.common;

public enum ReturnEnum {

    /**
     * 成功
     */
    SUCCESS("0", "success"),

    /**
     * 系统错误
     */
    ERROR_0001("0001", "system error"),
    /**
     * 参数校验失败
     */
    ERROR_0002("0002", "Param Validate Error"),
    ERROR_0003("0003", "文件格式错误"),
    ERROR_0004("0004", "文件内容错误"),
    ERROR_0005("0005", "文件不存在"),
    ERROR_0006("0006", "请勿重复提交"),
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

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

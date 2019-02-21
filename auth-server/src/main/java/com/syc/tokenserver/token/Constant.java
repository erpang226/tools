package com.syc.tokenserver.token;

/**
 * jwt常量
 *
 * @author syc
 * @version [版本号, 2018/1/24]
 * @see [可以参考 ]
 */
public class Constant {
    /**
     * jwt key 加密密码
     */
    public static final String JWT_SECRET = "agdsgh7ysdufasagaert5yk9h/lom";
    /**
     * 业务token 有效时间,半小时
     */
    public static final int BIZ_JWT_TTL = 12 * 60 * 60 * 1000;
    /**
     * refreshToken 有效时间,30分钟
     */
    public static final int REFRESH_JWT_TTL = 30 * 60 * 1000;
    /**
     * token 服务名称
     */
    public static final String TOKEN_SERVICE = "tokenService";
    /**
     * token 刷新方法
     */
    public static final String CREATE_REFRESH_TOKEN_METHOD = "createRefreshToken";

}
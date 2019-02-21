package com.syc.tokenserver.token;

import lombok.Data;

import java.util.List;


/**
 * jwt payload
 *
 * @author syc
 * @version [创建时间, 18-5-24 下午8:02]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
public class PayLoad {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 客户端编号
     */
    private int client;

    /**
     * 角色
     */
    private List<String> roles;

    public PayLoad() {

    }

    public PayLoad(String userId, String userType, int client, List<String> roles) {
        this.userId = userId;
        this.userType = userType;
        this.client = client;
        this.roles = roles;
    }
}

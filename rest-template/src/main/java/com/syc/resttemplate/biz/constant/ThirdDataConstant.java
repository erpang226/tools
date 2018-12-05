package com.syc.resttemplate.biz.constant;

/**
 * 描述
 *
 * @author syc
 * @version [版本号, 18-12-5 下午1:58]
 * @see [可以参考 ]
 */
public class ThirdDataConstant {
    /**
     * 创建时间
     */
    public static final String CREATE_TIME = "createTime";
    /**
     * 关键字
     */
    public static final String KEY_WORD = "keyWord";
    /**
     * 查询条件
     */
    public static final String QUERY_CONDITION = "queryCondition";
    /**
     * 条用调用者
     */
    public static final String INVOKER = "invoker";
    /**
     * id
     */
    public static final String ID = "id";

    private ThirdDataConstant() {

    }

    public static ThirdDataConstant init() {
        return new ThirdDataConstant();
    }
}

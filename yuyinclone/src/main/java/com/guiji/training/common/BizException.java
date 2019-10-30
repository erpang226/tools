package com.guiji.training.common;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/8/21
 * author: song yong chang
 */
import lombok.Data;

/**
 * 业务异常定义
 *
 * @author syc
 * @version [版本号, 2018/2/8]
 * @see [可以参考 ]
 */
@Data
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -5466180152813164957L;

    private ReturnEnum returnEnum;

    public BizException(ReturnEnum returnEnum, Throwable throwable) {
        super(returnEnum.getMsg(), throwable);
        this.returnEnum = returnEnum;
    }

    public BizException(ReturnEnum returnEnum) {
        super(returnEnum.getMsg());
        this.returnEnum = returnEnum;
    }

    public BizException(String msg) {
        super(msg);
    }


}

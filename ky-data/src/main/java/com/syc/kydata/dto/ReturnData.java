package com.syc.kydata.dto;

import com.syc.kydata.config.DirectReturn;
import lombok.Data;

/**
 * Description:
 *
 * 搜客宝数据,注册回调数据封装,所有字段名称固定,不允许修改
 * @version V1.0
 * date: 2019/9/2
 * author: song yong chang
 */
@Data
@DirectReturn
public class ReturnData {
    private Integer error_code;
    private boolean success;
    private String message;
    private Object data;


    public static ReturnData success(){
        ReturnData data=new ReturnData();
        data.setError_code(0);
        data.setMessage("");
        data.setSuccess(true);

        return data;
    }

    public static ReturnData success(Object data){
        ReturnData returnData=new ReturnData();
        returnData.setError_code(0);
        returnData.setMessage("");
        returnData.setSuccess(true);
        returnData.setData(data);

        return returnData;
    }

}

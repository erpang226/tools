package com.guiji.training.common;

import java.io.Serializable;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/8/21
 * author: song yong chang
 */
public class Result {

    private Result(){
    }

    public static <T> Data<T> getSuccessResult(T object) {

        return new Data<>(object);
    }

    public static <T> Data<T> getSuccessResult() {

        return new Data<>();
    }

    public static Data getErrorResult(ReturnEnum returnEnum) {
        return new Result.Data(returnEnum);
    }

    public static <T> Data<T> getErrorResult(ReturnEnum returnEnum, T object) {
        return new Result.Data<>(returnEnum,object);
    }

    @lombok.Data
    public static class Data<T> implements Serializable {

        private static final long serialVersionUID = 7164309861635874599L;
        /**
         * 返回码
         */
        private String code;
        /**
         * 返回信息
         */
        private String msg;
        /**
         * 返回数据
         */
        private T data;


        private Data() {
            this.code = ReturnEnum.SUCCESS.getCode();
            this.msg = ReturnEnum.SUCCESS.getMsg();

        }

        private Data(T obj) {
            this.code = ReturnEnum.SUCCESS.getCode();
            this.msg = ReturnEnum.SUCCESS.getMsg();
            this.data = obj;
        }
        private Data(ReturnEnum returnEnum) {
            this.code = returnEnum.getCode();
            this.msg = returnEnum.getMsg();
        }

        private Data(ReturnEnum returnEnum,T obj) {
            this.code = returnEnum.getCode();
            this.msg = returnEnum.getMsg();
            this.data = obj;
        }
    }
}
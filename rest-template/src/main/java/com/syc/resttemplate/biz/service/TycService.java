package com.syc.resttemplate.biz.service;

import com.alibaba.fastjson.JSONObject;
import com.syc.resttemplate.config.BizException;

public interface TycService {


    /**
     * 365-获取企业基本信息（含主要人员）
     *
     * @param name    企业名称
     * @param invoker 接口调用者
     * @see "https://open.tianyancha.com/open/365"
     */
    JSONObject getEnterpriseBasicInfo(String name, String invoker) throws BizException;


    /**
     * 421-动产抵押
     *
     * @param name    企业全名
     * @param pageNum 当前页数（默认第一页，每页20条）
     * @param invoker 接口调用者
     * @see "https://open.tianyancha.com/open/421"
     */
    JSONObject getDCDY(String name, int pageNum, String invoker) throws BizException;

    /**
     * 767-行政处罚--信用中国（新版）
     *
     * @param name    公司名称
     * @param pageNum 当前页数（默认第一页）
     * @see "https://open.tianyancha.com/open/767"
     */
    JSONObject getXZCF(String name, int pageNum, String invoker) throws BizException;

    /**
     * 391-经营异常
     *
     * @param name    公司名称
     * @param pageNum 当前页数（默认第一页）
     * @see "https://open.tianyancha.com/open/391"
     */
    JSONObject getJYYC(String name, int pageNum, String invoker) throws BizException;

    /**
     * 386-开庭公告
     *
     * @param name    公司名称
     * @param pageNum 当前页数（默认第一页）
     * @see "https://open.tianyancha.com/open/386"
     */
    JSONObject getKTGG(String name, int pageNum, String invoker) throws BizException;

    /**
     * 389-被执行人
     *
     * @param name    公司名称
     * @param pageNum 当前页数（默认第一页）
     * @see "https://open.tianyancha.com/open/389"
     */
    JSONObject getBZXR(String name, int pageNum, String invoker) throws BizException;

    /**
     * 385-法院公告
     *
     * @param name    公司名称
     * @param pageNum 当前页数（默认第一页）
     * @see "https://open.tianyancha.com/open/385"
     */
    JSONObject getFYGG(String name, int pageNum, String invoker) throws BizException;

    /**
     * 384-法律诉讼（法院判决）
     *
     * @param name    公司名称
     * @param pageNum 当前页数（默认第一页）
     * @see "https://open.tianyancha.com/open/384"
     */
    JSONObject getFYPJ(String name, int pageNum, String invoker) throws BizException;

    /**
     * 777-失信人（新版）
     *
     * @param name    公司名称
     * @param pageNum 当前页数（默认第一页）
     * @see "https://open.tianyancha.com/open/777"
     */
    JSONObject getSXR(String name, int pageNum, String invoker) throws BizException;

    /**
     * 实名认证(尊享版)
     *
     * @param name   姓名
     * @param idcard 身份证号
     * @return
     */
    JSONObject getSMRZ(String name, String idcard, String invoker) throws BizException;

    /**
     * 404-股权出质
     *
     * @param name    公司名称
     * @param pageNum 当前页数（默认第一页）
     * @see "https://open.tianyancha.com/open/404"
     */
    JSONObject getGQCZ(String name, int pageNum, String invoker) throws BizException;

    /**
     * 身份证OCR识别（尊享版）
     *
     * @param imageId 图片ID
     * @return
     */
    JSONObject getSFZSB(String imageId, String invoker) throws BizException;

    /**
     * 银行卡OCR识别
     *
     * @param imageId 图片ID
     * @return
     */
    JSONObject getYHKSB(String imageId, String invoker) throws BizException;

}

package com.syc.resttemplate.biz.service;

import com.alibaba.fastjson.JSONObject;
import com.syc.resttemplate.config.BizException;

import java.util.List;

/**
 * 数据宝数据服务
 *
 * @author syc
 * @version 1.0
 * 18-11-29 上午11:04
 * @see
 */
public interface SjbService {


    /**
     * 企业基本信息核验
     *
     * @param name    您申请的API的key值
     * @param type    企业名称/统一社会信用代码/注册号（备注：企业名称包含括号时一定要传英文状态下的括号）
     * @param entType 企业查询类型（默认为0(企业)，无需输入,1：个体工商户）
     */
    JSONObject getEnterpriseBasicInfo(String name, String type, String entType, String invoker) throws BizException;

    /**
     * 企业动产抵押核验
     *
     * @param name 企业全名
     * @param skip 跳过条目数（默认为 0）
     * @return
     */
    JSONObject getDCDY(String name, int skip, String invoker) throws BizException;

    /**
     * 企业动产抵押核验
     *
     * @param name 企业全名/注册号/统一社会信用代码
     * @param skip 跳过条目数（默认为 0）
     * @return
     */
    JSONObject getXZCF(String name, int skip, String invoker) throws BizException;

    /**
     * 企业经营异常核验
     *
     * @param name 企业全名
     * @param skip 跳过条目数（默认为 0）
     * @return
     */
    JSONObject getJYYC(String name, int skip, String invoker) throws BizException;

    /**
     * 企业开庭公告核验
     *
     * @param name 企业全名
     * @param skip 跳过条目数（默认为 0）
     * @return
     */
    JSONObject getKTGG(String name, int skip, String invoker) throws BizException;

    /**
     * 被执行人核验
     *
     * @param name 企业全名
     * @param skip 跳过条目数（默认为 0）
     * @return
     */
    JSONObject getBZXR(String name, int skip, String invoker) throws BizException;

    /**
     * 法院公告信息核验
     *
     * @param name 企业全名
     * @param skip 跳过条目数（默认为 0）
     * @return
     */
    JSONObject getFYGG(String name, int skip, String invoker) throws BizException;

    /**
     * 法院判决信息核验
     *
     * @param name 企业全名
     * @param skip 跳过条目数（默认为 0）
     * @return
     */
    JSONObject getFYPJ(String name, int skip, String invoker) throws BizException;

    /**
     * 失信人信息核验
     *
     * @param keyword 企业全称/注册号/统一社会信用代码
     * @param skip    跳过条目数（默认为 0）
     * @return
     */
    JSONObject getSXR(String keyword, int skip, String invoker) throws BizException;

    /**
     * 实名认证(尊享版)
     *
     * @param name   姓名
     * @param idcard 身份证号
     * @return
     */
    JSONObject getSMRZ(String name, String idcard, String invoker) throws BizException;

    /**
     * 股权出质
     *
     * @param keyword 企业全名
     * @param skip    跳过条目数（默认为 0）
     * @return
     */
    JSONObject getGQCZ(String keyword, int skip, String invoker) throws BizException;

    /**
     * 身份证OCR识别（尊享版）
     *
     * @param imageId 图片ID（根据接口《数据宝图片上传接口文档.pdf》返回值“data”获取，例如：“a54ad2ce022b4da689d9081a5eaeb4f8”）
     * @return
     */
    JSONObject getSFZSB(String imageId, String invoker) throws BizException;

    /**
     * 银行卡OCR识别
     *
     * @param imageId 图片ID（根据接口《数据宝图片上传接口文档.pdf》返回值“data”获取，例如：“a54ad2ce022b4da689d9081a5eaeb4f8”）
     * @return
     */
    JSONObject getYHKSB(String imageId, String invoker) throws BizException;


    /**
     * 获取前N条已入库的企业基本信息，根据创建时间排序
     *
     * @param name    {@link SjbService#getEnterpriseBasicInfo(String, String, String, String)}
     * @param limit   查询条数
     * @param invoker 调用者
     */
    List<JSONObject> queryEnterpriseBasicInfo(String name, int limit, String invoker);

    /**
     * 企业动产抵押核验
     * {@link SjbService#getDCDY(String, int, String)}
     *
     * @param name  企业全名
     * @param limit 查询条数
     * @return
     */
    List<JSONObject> queryDCDY(String name, int limit, String invoker);

    /**
     * 行政处罚抵押核验
     * {@link SjbService#getXZCF(String, int, String)}
     *
     * @param name  企业全名/注册号/统一社会信用代码
     * @param limit 查询条数
     * @return
     */
    List<JSONObject> queryXZCF(String name, int limit, String invoker);

    /**
     * 企业经营异常核验
     * {@link SjbService#queryJYYC(String, int, String)}
     *
     * @param name  企业全名
     * @param limit 查询条数
     * @return
     */
    List<JSONObject> queryJYYC(String name, int limit, String invoker);

    /**
     * 企业开庭公告核验
     * {@link SjbService#queryKTGG(String, int, String)}
     *
     * @param name  企业全名
     * @param limit 查询条数
     * @return
     */
    List<JSONObject> queryKTGG(String name, int limit, String invoker);

    /**
     * 被执行人核验
     * {@link SjbService#getBZXR(String, int, String)}
     *
     * @param name  企业全名
     * @param limit 查询条数
     * @return
     */
    List<JSONObject> queryBZXR(String name, int limit, String invoker);

    /**
     * 法院公告信息核验
     * {@link SjbService#getFYGG(String, int, String)}
     *
     * @param name  企业全名
     * @param limit 查询条数
     * @return
     */
    List<JSONObject> queryFYGG(String name, int limit, String invoker);

    /**
     * 法院判决信息核验
     * {@link SjbService#getFYPJ(String, int, String)}
     *
     * @param name  企业全名
     * @param limit 查询条数
     * @return
     */
    List<JSONObject> queryFYPJ(String name, int limit, String invoker);

    /**
     * 失信人信息核验
     * {@link SjbService#getSXR(String, int, String)}
     *
     * @param keyword 企业全称/注册号/统一社会信用代码
     * @param limit   查询条数
     * @return
     */
    List<JSONObject> querySXR(String keyword, int limit, String invoker);

    /**
     * 实名认证(尊享版)
     * {@link SjbService#getSMRZ(String, String, String)}
     *
     * @param name   姓名
     * @param idcard 身份证号
     * @param limit  查询条数
     * @return
     */
    List<JSONObject> querySMRZ(String name, String idcard, int limit, String invoker);

    /**
     * 股权出质
     * {@link SjbService#getGQCZ(String, int, String)}
     *
     * @param keyword 企业全名
     * @param limit   查询条数
     * @return
     */
    List<JSONObject> queryGQCZ(String keyword, int limit, String invoker);

    /**
     * 身份证OCR识别（尊享版）
     * {@link SjbService#getSFZSB(String, String)}
     *
     * @param imageId 图片ID（根据接口《数据宝图片上传接口文档.pdf》返回值“data”获取，例如：“a54ad2ce022b4da689d9081a5eaeb4f8”）
     * @param limit   查询条数
     * @return
     */
    List<JSONObject> querySFZSB(String imageId, int limit, String invoker);

    /**
     * 银行卡OCR识别
     * {@link SjbService#getYHKSB(String, String)}
     *
     * @param imageId 图片ID（根据接口《数据宝图片上传接口文档.pdf》返回值“data”获取，例如：“a54ad2ce022b4da689d9081a5eaeb4f8”）
     * @param limit   查询条数
     * @return
     */
    List<JSONObject> queryYHKSB(String imageId, int limit, String invoker);


}

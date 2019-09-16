package com.hiynn.service;


import com.hiynn.dictionary.Result;
import com.hiynn.exception.BizException;

import java.io.File;
import java.io.InputStream;

/**
 * 文件服务(上传,下载)
 *
 * @author syc
 * @version [版本号, 2018/7/11]
 * @see [可以参考 ]
 */
public interface FileService {

    /**
     * @param inputStream
     * @return
     * @throws BizException
     */
    Result uploadFile(String path, InputStream inputStream) throws BizException;

    /**
     * 删除文件
     *
     * @param fileId
     * @return
     * @throws BizException
     */
    Result deleteFile(String fileId) throws BizException;


    /**
     * 下载文件
     *
     * @return
     */
    File downloadFile(String fileId) throws BizException;


}
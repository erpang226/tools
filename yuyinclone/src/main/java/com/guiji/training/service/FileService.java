package com.guiji.training.service;


import com.guiji.training.common.BizException;
import com.guiji.training.common.Result;

import java.io.File;
import java.io.IOException;
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
     *
     *
     * @param name 文件名称
     * @param inputStream   文件流
     * @return Result.Data
     */
    Result.Data uploadFile(String name, InputStream inputStream);

    /**
     * @param bytes 文件流
     * @return Result.Data
     */
    Result.Data uploadFile(Long packageId, String fileName, byte[] bytes, String text) throws IOException;

    /**
     * 压缩wavs
     *
     * @param packageId
     * @return
     */
    Result.Data<String> zipFiles(Long packageId) throws IOException;

    /**
     * 删除文件
     *
     * @param id
     * @return
     * @throws BizException
     */
    void deleteFile(Long id) throws IOException;

    /**
     * 下载文件
     *
     * @return
     */
    File downloadFile(String fileId) throws BizException;


}
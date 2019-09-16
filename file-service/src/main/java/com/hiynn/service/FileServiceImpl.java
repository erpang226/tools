package com.hiynn.service;

import com.hiynn.dictionary.Result;
import com.hiynn.dictionary.ReturnEnum;
import com.hiynn.exception.BizException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${file-root-path}")
    private String rootPath;


    @Override
    public Result uploadFile(String name, InputStream inputStream) throws BizException {

        String localPath = rootPath + File.separator + name;
        OutputStream output = null;
        try {
            File file = new File(localPath);
            if (!file.getParentFile().exists()) {
                if (file.getParentFile().mkdirs()) {
                    logger.info("创建文件夹【{}】成功", file.getParentFile().getPath());
                } else {
                    logger.error("创建文件夹【{}】失败", file.getParentFile().getPath());
                }
            }
            //保存文件到指定路径
            output = new FileOutputStream(file);
            IOUtils.copy(inputStream, output);
            return Result.getSuccessResult();

        } catch (Exception e) {
            logger.error("文件上传失败{}", name, e);
            throw new BizException(ReturnEnum.FAIL);
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
                inputStream.close();
            } catch (Exception e) {
                logger.error("文件上传失败，网络异常", e);
            }
        }

    }

    @Override
    public Result deleteFile(String fileId) throws BizException {

        return null;
    }


    @Override
    public File downloadFile(String fileId) throws BizException {
        return null;
    }


}

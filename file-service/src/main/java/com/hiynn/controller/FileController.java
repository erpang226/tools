package com.hiynn.controller;

import com.hiynn.dictionary.Result;
import com.hiynn.dictionary.ReturnEnum;
import com.hiynn.exception.BizException;
import com.hiynn.service.FileService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Description:
 * CreateTime: 19-6-25 下午5:17
 *
 * @author syc
 * @version 1.0
 */
@RestController
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileService fileService;

    @Value("${file-root-path}")
    private String rootPath;


    @GetMapping(value = "/childrenFile")
    public String[] getChildrenFile() {
        File root = new File(rootPath);
        return root.list();
    }

    @GetMapping(value = "/childrenFile/{path}")
    public String[] getChildrenFile(@PathVariable String path) {
        File root = new File(rootPath + File.separator + path);
        return root.list();
    }


    /**
     * RestTemplate rest = new RestTemplate();
     * FileSystemResource resource = new FileSystemResource(file);
     * MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
     * param.add("file", resource);
     * param.add("name", wordDocName);
     * rest.postForObject(fileServer+"/uploadFile", param, Result.class);
     *
     * @param name
     * @param file
     * @return
     */
    @ApiOperation(value = "上传文件", httpMethod = "POST")
    @PostMapping(value = "/uploadFile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "文件名称", defaultValue = "文件名称", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "file", value = "待上传的文件", required = true, dataType = "File", paramType = "body")
    })
    public Result uploadFile(String name, MultipartFile file) {

        try {

            return fileService.uploadFile(name, file.getInputStream());
        } catch (BizException e) {
            LOGGER.error("", e);
            return Result.getErrorResult(ReturnEnum.ERROR_000001);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return null;
    }

    @PostMapping(value = "/uploadFileBytes")
    public Result uploadFileBytes(String name, byte[] bytes) {

        try {
            return fileService.uploadFile(name, new ByteArrayInputStream(bytes));
        } catch (BizException e) {
            LOGGER.error("", e);
            return Result.getErrorResult(ReturnEnum.ERROR_000001);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return null;
    }



    @ApiOperation(value = "下载文件", httpMethod = "GET")
    @GetMapping(value = "/downloadFile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "文件相对路径", required = true, dataType = "String", paramType = "body")
    })
    public Result downloadFile(String path, HttpServletResponse response) {

        File downloadFile = new File(rootPath + File.separator + path);
        LOGGER.info("file path: {}", downloadFile.getPath());

        if (!downloadFile.exists()) {
            return Result.getErrorResult(ReturnEnum.ERROR_000003);
        }


        try {
            response.setContentType("application/octet-stream");
            response.setContentLength((int) downloadFile.length());
            response.setHeader("Content-Disposition",
                    "attachment; fileName=" + downloadFile.getName() + ";filename*=utf-8''"
                            + URLEncoder.encode(downloadFile.getName(), "UTF-8"));

            InputStream myStream = new FileInputStream(downloadFile);
            IOUtils.copy(myStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            LOGGER.error("download file failed", e);
            return Result.getErrorResult(ReturnEnum.FAIL);
        }

        return Result.getSuccessResult();
    }

}

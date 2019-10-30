package com.guiji.training.controller;

import com.guiji.training.common.BizException;
import com.guiji.training.common.Result;
import com.guiji.training.common.ReturnEnum;
import com.guiji.training.entity.Package;
import com.guiji.training.repositry.FileRepository;
import com.guiji.training.repositry.PackageRepository;
import com.guiji.training.service.FileService;
import com.guiji.training.service.PackageService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/8/21
 * author: song yong chang
 */
@RestController
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private PackageService packageService;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private FileRepository fileRepository;

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
     * zip语料文件上传
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/uploadZip")
    public Result.Data uploadZip(MultipartFile file) {
        String name = file.getOriginalFilename();
        try {
            return fileService.uploadFile(name, file.getInputStream());
        } catch (BizException e) {
            LOGGER.error("file [{}] upload failed", name, e);
            return Result.getErrorResult(e.getReturnEnum());
        } catch (Exception e) {
            LOGGER.error("file [{}] upload failed", name, e);
            return Result.getErrorResult(ReturnEnum.ERROR_0001);
        }
    }

    @PostMapping(value = "/uploadFileBytes")
    public Result.Data uploadFileBytes(String name, byte[] bytes) {

        try {
            return fileService.uploadFile(name, new ByteArrayInputStream(bytes));
        } catch (BizException e) {
            LOGGER.error("", e);
            return Result.getErrorResult(ReturnEnum.ERROR_0001, name);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return null;
    }

    /**
     * 获取packageId
     *
     * @return Data
     */
    @GetMapping(value = "/packageId")
    public Result.Data<Long> packageId(String nickName, int total, String userId) {
        try {
            LOGGER.info("nickName:{} total:{} userId:{}", nickName, total, userId);
            // 如果当前用户有未完成的批次则返回该批次号,没有就创建一个新的
            Package p = packageRepository.findFirstByCreateByAndStatusOrderByCreateTimeDesc(userId, 0);

            if (null == p) {
                Package pr = packageService.saveNewPackage(nickName + new Date().getTime(), userId, total);
                LOGGER.info("pr - {}", pr.getName());
                return Result.getSuccessResult(pr.getId());
            } else {
                long id = p.getId();
                LOGGER.info("返回原有未完成的packageId - {}", id);
                return Result.getSuccessResult(id);
            }
        } catch (Exception e) {
            LOGGER.error("创建默认package失败", e);
            return Result.getErrorResult(ReturnEnum.ERROR_0001, 0L);
        }
    }

    /**
     * 上传单个wav文件信息
     *
     * @param packageId 包id
     * @param wavUrl    文件资源路径
     * @param text      音频对应的文本信息
     * @return Data
     */
    @PostMapping(value = "/uploadWav")
    public Result.Data uploadWav(@Param("packageId") Long packageId, @Param("wavUrl") String wavUrl, @Param("text") String text) {

        LOGGER.info("uploadWav: {} {} {}", packageId, wavUrl, text);
        try {
            byte[] bytes = getFileBytes(wavUrl);
            LOGGER.info("bytes size [{}]", bytes.length);
            URL url = new URL(wavUrl);
            String[] paths = url.getFile().split("/");
            String fileName = paths[paths.length - 1];
            fileService.uploadFile(packageId, fileName, bytes, text);
        } catch (BizException be) {
            LOGGER.error("上传文件业务异常:{}", be.getReturnEnum().getMsg());
            return Result.getErrorResult(be.getReturnEnum());
        } catch (Exception e) {
            LOGGER.error("上传文件失败:[{}] [{}] [{}]", packageId, wavUrl, text, e);
            return Result.getErrorResult(ReturnEnum.ERROR_0005, "上传文件失败");
        }
        return Result.getSuccessResult();
    }

    /**
     * 语料训练列表查询
     *
     * @return
     */
    @GetMapping(value = "/packages/{page}/{size}")
    public Result.Data packages(@PathVariable int page, @PathVariable int size, String name, String status) {
        PageRequest pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createTime");
        Page<Package> pageObject = packageRepository.findAll((Specification<Package>) (root, query, cb) -> {
            Predicate predicate = cb.and();
            if (!StringUtils.isEmpty(name)) {
                predicate = cb.like(root.get("name"), "%" + Base64.getEncoder().encodeToString(name.getBytes()) + "%");
            }
            if (!StringUtils.isEmpty(status)) {
                predicate = cb.and(predicate, cb.equal(root.get("status"), Integer.valueOf(status)));
            }
            return predicate;
        }, pageable);

        return Result.getSuccessResult(pageObject);
    }

    /**
     * 语料详情列表查询
     *
     * @return
     */
    @GetMapping(value = "/files/{packageId}/{page}/{size}")
    public Result.Data files(@PathVariable Long packageId, @PathVariable int page, @PathVariable int size) {

        PageRequest pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createTime");
        Page<com.guiji.training.entity.File> pageObject = fileRepository.findAllByPackageId(packageId, pageable);


        return Result.getSuccessResult(pageObject);
    }

    @PostMapping(value = "/delete/package/{id}")
    public Result.Data deletePacke(@PathVariable("id") Long id) {
        try {
            packageService.deletePackageAndFiles(id);
        } catch (Exception e) {
            LOGGER.error("delete package [{}] error", id, e);
            return Result.getErrorResult(ReturnEnum.ERROR_0001);
        }
        return Result.getSuccessResult();
    }

    @PostMapping(value = "/delete/file/{id}")
    public Result.Data deleteFile(@PathVariable("id") Long id) {
        try {
            fileService.deleteFile(id);
        } catch (Exception e) {
            LOGGER.error("delete file [{}] error", id, e);
            return Result.getErrorResult(ReturnEnum.ERROR_0001);
        }
        return Result.getSuccessResult();
    }


    private byte[] getFileBytes(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        List<MediaType> list = new ArrayList<>();
        // 指定下载文件类型
        list.add(MediaType.APPLICATION_OCTET_STREAM);
        header.setAccept(list);

        HttpEntity<byte[]> request = new HttpEntity<>(header);
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, request, byte[].class);
        return response.getBody();
    }


    @DeleteMapping(value = "/deleteTest/{id}")
    public Result.Data testDelete(@PathVariable("id") String id) {
        LOGGER.info("id: {}", id);
        return Result.getSuccessResult(id);
    }

}

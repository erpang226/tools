package com.guiji.training.service.impl;

import com.guiji.training.common.BizException;
import com.guiji.training.common.ReturnEnum;
import com.guiji.training.entity.Package;
import com.guiji.training.repositry.FileRepository;
import com.guiji.training.repositry.PackageRepository;
import com.guiji.training.service.PackageService;
import com.guiji.training.utiles.FileUtil;
import com.guiji.training.utiles.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/8/22
 * author: song yong chang
 */
@Service
@Slf4j
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private FileRepository fileRepository;


    @Value("${file-root-path}")
    private String rootPath;

    @Override
    public Package saveNewPackage(String name, String userId, int total) {
        String url = File.separator + UUIDUtil.get();
        Package s = new Package(null, null, 0, null, userId, new Date(), url, total);
        s.setName(name);
        File file = new File(rootPath + url);
        if (file.mkdirs()) {
            return packageRepository.save(s);
        } else {
            throw new BizException("创建文件失败");
        }
    }

    @Transactional
    @Override
    public void deletePackageAndFiles(Long id) throws IOException {
        // 删除文件
        Optional<Package> optional = packageRepository.findById(id);
        Package p = optional.orElseThrow(() -> new BizException(ReturnEnum.ERROR_0005));
        File packageDir = new File(rootPath + File.separator + p.getUrl());
        FileUtil.deleteDirectory(packageDir);
        packageRepository.deleteById(id);
        fileRepository.deleteAllByPackageId(id);
    }

}






















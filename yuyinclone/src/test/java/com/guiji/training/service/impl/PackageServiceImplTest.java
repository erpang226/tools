package com.guiji.training.service.impl;

import com.guiji.training.entity.File;
import com.guiji.training.entity.Package;
import com.guiji.training.repositry.FileRepository;
import com.guiji.training.repositry.PackageRepository;
import com.guiji.training.service.PackageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PackageServiceImplTest {

    @Autowired
    private PackageService packageService;

    @Autowired
    private PackageRepository packageRepository;


    @Autowired
    private FileRepository fileRepository;


    @Test
    public void saveNewPackage() {
        PageRequest pageable = PageRequest.of(1, 10, Sort.Direction.DESC, "createTime");
        Page<File> pages = fileRepository.findAllByPackageId(2L, pageable);

        pages.getContent();
    }

    /*
    {
    "content": [{}], // 数据列表
    "last": true, // 是否最后一页
    "totalPages": 1, // 总页数
    "totalElements": 1, // 数据总数
    "sort": null, // 排序
    "first": true, // 是否首页
    "numberOfElements": 1, // 本页数据条数
    "size": 10, // 每页长度
    "number": 0 // 当前页序号
    }
     */
    @Test
    public void testPackageRepository() {
        PageRequest pageable = PageRequest.of(1, 10, Sort.Direction.DESC, "createTime");
        Page<Package> page = packageRepository.findAll(pageable);

        page.getTotalElements();
        page.getTotalPages();
        page.get();
        List<Package> packages = page.getContent();


    }


}
package com.guiji.training.repositry;

import com.guiji.training.common.BizException;
import com.guiji.training.common.ReturnEnum;
import com.guiji.training.entity.Package;
import com.guiji.training.service.FileService;
import com.guiji.training.service.PackageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private PackageService packageService;

    @Autowired
    private FileService fileService;

    @Test
    public void testQuery(){

        Iterable<Package> list = packageRepository.findAll();
        list.forEach(i-> System.out.println(i.getId()));
    }


    @Test
    public void testFile() {
        fileRepository.deleteAllByPackageId(1L);
    }


    @Test
    public void testPackage() throws IOException {
        packageService.deletePackageAndFiles(1L);
    }

    @Test
    public void testDeleteFile() throws IOException {
        fileService.deleteFile(34L);
    }

    @Test
    public void testUpdate() {

        Long packageId = 2L;
        Optional<Package> optional = packageRepository.findById(packageId);
        Package p = optional.orElseThrow(() -> new BizException(ReturnEnum.ERROR_0005));
        com.guiji.training.entity.File f = new com.guiji.training.entity.File();
        f.setPackageId(packageId);

        long count = fileRepository.count(Example.of(f));
        if (count >= p.getTotal()) {
            p.setStatus(8);
            packageRepository.save(p);
        }
    }

    @Test
    public void testConditionFind() {

    }

    @Test
    public void testConditionFind1() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "createTime");
//        Page<Package> page = packageRepository.findAllByNameLikeAndStatus("5riF5oKmMTU2Njg", 0, pageable);
        int status = Integer.valueOf("0");
        System.out.println(status);
        Page<Package> pageObject = packageRepository.findAllByCondition("5riF5oKmMTU2Njg5OTQ1NzAzOA==",
                status, pageable);
        pageObject.getContent().forEach(p -> System.out.println(p.getName()));
    }


    @Test
    public void testOrder() {
        Package p = packageRepository.findFirstByOrderByCreateTimeDesc();
        System.out.println(p);
    }


    @Test
    public void testFindFirstByCreateByAndStatusOrderByCreateTimeDesc() {
        Package p = packageRepository.findFirstByCreateByAndStatusOrderByCreateTimeDesc("admin1", 0);
        System.out.println(p);
    }


    @Test
    public void testLike() {


    }


}
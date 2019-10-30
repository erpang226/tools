package com.guiji.training.repositry;

import com.guiji.training.entity.Package;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.Predicate;
import java.util.Base64;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PackageTestRepositoryTest {

    @Autowired
    private PackageTestRepository packageTestRepository;

    @Test
    public void findFirstOrderByCreateTimeDes() {
        Package aPackage = packageTestRepository.findFirstByOrderByCreateTimeDesc();
        System.out.println(aPackage);
    }

    @Test
    public void testFindByCreateBy() {
        List<Package> l = packageTestRepository.findByCreateBy("admin");
        l.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByCreateByPage() {
        Pageable pageable = PageRequest.of(0, 10);
        packageTestRepository.findByCreateBy("admin", pageable).getContent().forEach(System.out::println);
    }

    @Test
    public void testFindSpecification() {

        String name = "";
        String status = "8";

        Pageable pageable = PageRequest.of(0, 10);
        Page<Package> page = packageTestRepository.findAll((Specification<Package>) (root, query, cb) -> {
            Predicate predicate = cb.and();
            if (!StringUtils.isEmpty(name)) {
                predicate = cb.like(root.get("name"), "%" + Base64.getEncoder().encodeToString("清悦".getBytes()) + "%");
            }
            if (!StringUtils.isEmpty(status)) {
                predicate = cb.and(predicate, cb.equal(root.get("status"), Integer.valueOf(status)));
            }
            return predicate;
        }, pageable);

        page.getContent().forEach(System.out::println);

    }


}
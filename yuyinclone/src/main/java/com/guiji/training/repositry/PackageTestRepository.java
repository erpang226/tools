package com.guiji.training.repositry;

import com.guiji.training.entity.Package;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/8/20
 * author: song yong chang
 */
public interface PackageTestRepository extends Repository<Package, Long>, JpaSpecificationExecutor<Package> {

    List<Package> findByCreateBy(String createBy);

    Package findFirstByOrderByCreateTimeDesc();

    Page<Package> findByCreateBy(String createBy, Pageable pageable);

}

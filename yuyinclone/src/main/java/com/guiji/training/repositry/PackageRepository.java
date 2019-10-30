package com.guiji.training.repositry;

import com.guiji.training.entity.Package;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/8/20
 * author: song yong chang
 */
public interface PackageRepository extends JpaRepository<Package, Long>, JpaSpecificationExecutor<Package> {

    @Query(value = "select p from Package p where p.name like CONCAT('%',?1,'%') and p.status=?2")
    Page<Package> findAllByCondition(String name, Integer status, PageRequest pageable);

    @Query(value = "select p from Package p where p.name like CONCAT('%',?1,'%')")
    Page<Package> findAllByNameLike(String name, PageRequest pageable);

    @Query(value = "select p from Package p where p.status=?1")
    Page<Package> findAllByStatus(Integer status, PageRequest pageable);

    Package findFirstByCreateByAndStatusOrderByCreateTimeDesc(String createBy, int status);

    Package findFirstByOrderByCreateTimeDesc();

    Page<Package> findByNameLikeAndStatus(String name, int status, Pageable pageable);
}

package com.guiji.training.repositry;

import com.guiji.training.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/8/20
 * author: song yong chang
 */
public interface FileRepository extends JpaRepository<File, Long> {

    Page<File> findAllByPackageId(@Param("packageId") Long packageId, Pageable pageable);

    //    @Query(value = "delete from file where package_id=:packageId ", nativeQuery = true)
//    @Modifying
    @Transactional
    void deleteAllByPackageId(Long packageId);
}

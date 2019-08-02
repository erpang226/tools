package com.syc.javacodegenerator.dao;

import com.syc.javacodegenerator.entity.BladeRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author syc
 * Date  2019-08-02
 */
@Mapper
public interface BladeRoleDao {

    public BladeRole get(String id);

    public List<BladeRole> findList(BladeRole bladeRole);

    public List<BladeRole> findAllList();

    public int insert(BladeRole bladeRole);

    public int insertBatch(List<BladeRole> bladeRoles);

    public int update(BladeRole bladeRole);

    public int delete(BladeRole bladeRole);

}
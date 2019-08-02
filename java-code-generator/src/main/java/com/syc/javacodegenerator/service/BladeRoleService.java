package com.syc.javacodegenerator.service;

import com.syc.javacodegenerator.entity.BladeRole;

import java.util.List;

/**
 * Author syc
 * Date  2019-08-02
 */
public interface BladeRoleService {

    public BladeRole get(String id);

    public List<BladeRole> findList(BladeRole bladeRole);

    public List<BladeRole> findAllList();

    public int insert(BladeRole bladeRole);

    public int insertBatch(List<BladeRole> bladeRoles);

    public int update(BladeRole bladeRole);

    public int delete(BladeRole bladeRole);

}

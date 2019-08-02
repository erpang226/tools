package com.syc.javacodegenerator.service.impl;

import com.syc.javacodegenerator.dao.BladeRoleDao;
import com.syc.javacodegenerator.entity.BladeRole;
import com.syc.javacodegenerator.service.BladeRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author syc
 * Date  2019-08-02
 */
@Service
public class BladeRoleServiceImpl implements BladeRoleService {
    @Autowired
    private BladeRoleDao bladeRoleDao;
    
    @Override
    public BladeRole get(String id){
        return bladeRoleDao.get(id);
    }
    
    @Override
    public List<BladeRole> findList(BladeRole bladeRole) {
        return bladeRoleDao.findList(bladeRole);
    }
    
    @Override
    public List<BladeRole> findAllList() {
        return bladeRoleDao.findAllList();
    }
    
    @Override
    public int insert(BladeRole bladeRole) {
        return bladeRoleDao.insert(bladeRole);
    }
    
    @Override
    public int insertBatch(List<BladeRole> bladeRoles){
        return bladeRoleDao.insertBatch(bladeRoles);
    }
    
    @Override
    public int update(BladeRole bladeRole) {
        return bladeRoleDao.update(bladeRole);
    }
    
    @Override
    public int delete(BladeRole bladeRole) {
        return bladeRoleDao.delete(bladeRole);
    }

}

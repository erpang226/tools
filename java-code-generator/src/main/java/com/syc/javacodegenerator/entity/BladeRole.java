package com.syc.javacodegenerator.entity;

import java.io.Serializable;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Author syc
 * Date  2019-08-02
 */
public class BladeRole implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String tenantCode;
    private int parentId;
    private String roleName;
    private int sort;
    private String roleAlias;
    private int isDeleted;


    public BladeRole(){
    }

    public void setId (int id) {this.id = id;} 
    public int getId(){ return id;} 
    public void setTenantCode (String tenantCode) {this.tenantCode = tenantCode;} 
    public String getTenantCode(){ return tenantCode;} 
    public void setParentId (int parentId) {this.parentId = parentId;} 
    public int getParentId(){ return parentId;} 
    public void setRoleName (String roleName) {this.roleName = roleName;} 
    public String getRoleName(){ return roleName;} 
    public void setSort (int sort) {this.sort = sort;} 
    public int getSort(){ return sort;} 
    public void setRoleAlias (String roleAlias) {this.roleAlias = roleAlias;} 
    public String getRoleAlias(){ return roleAlias;} 
    public void setIsDeleted (int isDeleted) {this.isDeleted = isDeleted;} 
    public int getIsDeleted(){ return isDeleted;} 

}
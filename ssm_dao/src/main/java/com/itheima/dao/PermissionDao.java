package com.itheima.dao;

import com.itheima.pojo.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {

    /**
     * 根据角色ID查询用户权限
     * @param id
     * @return
     */
    public List<Permission> findPermissionByRoleId(String id);

    List<Permission> findAll();

    Permission findById(String id);

    void save(Permission permission);

    void deletePermission(String id);

    void deleteRolePermission(String id);
}

package com.itheima.service;

import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;

import java.util.List;

public interface RoleService {

    public List<Role> findAll(int page, int size);

    Role findById(String id);

    void save(Role role) throws Exception;

    List<Permission> findAllPermission(String id);

    void addPermissionToRole(String roleId, String[] ids);
}

package com.itheima.service;

import com.itheima.pojo.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll(int page, int size);

    Permission findById(String id);

    void save(Permission permission);

    void deletePermission(String id);
}

package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.PermissionDao;
import com.itheima.pojo.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        return permissionDao.findAll();
    }

    @Override
    public Permission findById(String id) {
        return permissionDao.findById(id);
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public void deletePermission(String id) {
        permissionDao.deleteRolePermission(id);
        permissionDao.deletePermission(id);
    }
}

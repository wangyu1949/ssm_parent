package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Role> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        return roleDao.findAll();
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void save(Role role) throws Exception {
        role.setRoleName(role.getRoleName().toUpperCase());
        roleDao.save(role);
    }

    /**
     * 查询指定ID的role对象所不具有的permission集合
     * @param id
     * @return
     */
    @Override
    public List<Permission> findAllPermission(String id) {
        List<Permission> permissionByRoleId = permissionDao.findPermissionByRoleId(id);
        List<Permission> all = permissionDao.findAll();
        ArrayList<String> ids = new ArrayList<>();
        for (Permission permission : permissionByRoleId) {
            ids.add(permission.getId());
        }
        ArrayList<Permission> permissions = new ArrayList<>();
        for (Permission permission : all) {
            if (!ids.contains(permission.getId())) {
                permissions.add(permission);
            }
        }
        return permissions;
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
//        System.out.println(Arrays.toString(ids) + "+++++++++++++++++++++++++++");
//        System.out.println(roleId + "---------------------------");
        for (String permissionId : ids) {
            roleDao.addPermissionToRole(roleId, permissionId);
        }
    }
}

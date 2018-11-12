package com.itheima.dao;

import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {

    /**
     * 根据用户ID查询角色信息
     * @param id
     * @return
     */
    public List<Role> findRoleByUserId(String id);


    /**
     * 查询所有角色的方法
     * @return
     */
    public List<Role> findAll();

    Role findById(String id);

    void save(Role role) throws Exception;

    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}

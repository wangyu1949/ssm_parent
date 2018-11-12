package com.itheima.dao;

import com.itheima.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersDao {
    List<Users> findAll();

    void save(Users users);

    Users findById(String id);

    Users findByUserName(String userName);

    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}

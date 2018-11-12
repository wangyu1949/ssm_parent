package com.itheima.service;

import com.itheima.pojo.Role;
import com.itheima.pojo.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsersService extends UserDetailsService {

    public List<Users> findAll(int page, int size) throws Exception;

    public void save(Users users) throws Exception;

    public Users findById(String id) throws Exception;


    List<Role> findAllRole(String id);

    void addRoleToUser(String userId, String[] ids);
}

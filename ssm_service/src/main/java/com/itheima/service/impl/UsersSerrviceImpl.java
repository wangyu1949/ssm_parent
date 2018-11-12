package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.RoleDao;
import com.itheima.dao.UsersDao;
import com.itheima.pojo.Role;
import com.itheima.pojo.Users;
import com.itheima.service.UsersService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersSerrviceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Users> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return usersDao.findAll();
    }

    @Override
    public void save(Users users) throws Exception {
        //使用加密盐对密码进行加密
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        usersDao.save(users);
    }

    @Override
    public Users findById(String id) throws Exception {
        return usersDao.findById(id);
    }

    /**
     * 查询用户不具有的角色的列表
     * @param id
     * @return
     */
    @Override
    public List<Role> findAllRole(String id) {
        List<Role> byAll = roleDao.findAll();

//        System.out.println(byAll + "=============================");

        List<Role> byUserId = roleDao.findRoleByUserId(id);
        List<String> roleIds = new ArrayList<>();
        for (Role role : byUserId) {
            roleIds.add(role.getId());
        }

//        System.out.println(byUserId + "============================");

        List<Role> roles = new ArrayList<>();

        for (Role role : byAll) {
            if (!roleIds.contains(role.getId())) {
                roles.add(role);
            }
        }

        return roles;
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) {
        for (String roleId : ids) {
            usersDao.addRoleToUser(userId, roleId);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

//        System.out.println(s);
//        System.out.println("=====================================================");

        Users users = null;
        try {
            users = usersDao.findByUserName(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        User user = new User(users.getUsername(), "{noop}" + users.getPassword(), users.getStatus() == 1, true, true, true, getAuthority(users.getRoles()));
        User user = new User(users.getUsername(), users.getPassword(), users.getStatus() == 1, true, true, true, getAuthority(users.getRoles()));

//        System.out.println(user);
//        System.out.println("===================================================");
        return user;
    }


    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

//    @Test
//    public void test() {
//        BCryptPasswordEncoder bCryptPasswordEncoder1 = new BCryptPasswordEncoder();
//        System.out.println(bCryptPasswordEncoder1.encode("user"));
//    }
}

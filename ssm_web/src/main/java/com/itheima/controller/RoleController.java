package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.service.RoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll")
    @PermitAll
    public ModelAndView findAll(@RequestParam(name = "pageNum", required = true, defaultValue = "1") Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer size) {
        List<Role> roles = roleService.findAll(page, size);
        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        ModelAndView mv = new ModelAndView();
        mv.addObject("rolePageInfo", rolePageInfo);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/findById")
    @PermitAll
    public ModelAndView findById(String id) {
        Role role = roleService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/save")
    @RolesAllowed("ADMIN")
    public ModelAndView save(Role role) throws Exception {
        roleService.save(role);
        return findAll(1, 5);
    }

    /**
     * 查询指定id的role对象所不具有的permission集合
     * @param id
     * @return
     */
    @RequestMapping("/findRoleByIdAndAddPermission")
    @PermitAll
    public ModelAndView findRoleByIdAndAddPermission(String id) {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = roleService.findAllPermission(id);
        mv.addObject("permissionList", permissions);
        mv.addObject("roleId", id);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole")
    @RolesAllowed("ADMIN")
    public ModelAndView addPermissionToRole(String roleId, String[] ids) {
        roleService.addPermissionToRole(roleId, ids);
        return findById(roleId);
    }
}

package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.pojo.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAll")
    @PermitAll
    public ModelAndView findAll(@RequestParam(name = "pageNum", required = true, defaultValue = "1") Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer size) {
        List<Permission> permissionList = permissionService.findAll(page, size);
        PageInfo<Permission> permissionPageInfo = new PageInfo<>(permissionList);
        ModelAndView mv = new ModelAndView();
        mv.addObject("permissionPageInfo", permissionPageInfo);
        mv.setViewName("permission-list");
        return mv;
    }


    /**
     * 根据ID查询permission详情
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    @PermitAll
    public ModelAndView findById(@RequestParam(name = "id") String id) {
        Permission permission = permissionService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("permission", permission);
        mv.setViewName("permission-show");
        return mv;
    }

    /**
     * 保存permission
     * @param permission
     * @return
     */
    @RequestMapping("/save")
    @RolesAllowed("ADMIN")
    public ModelAndView save(Permission permission) {
        permissionService.save(permission);
        return findAll(1, 5);
    }

    /**
     * 根据ID删除permission
     * @param id
     * @return
     */
    @RequestMapping("/deletePermission")
    @RolesAllowed("ADMIN")
    public ModelAndView deletePermission(String id) {
        permissionService.deletePermission(id);
        return findAll(1, 5);
    }
}

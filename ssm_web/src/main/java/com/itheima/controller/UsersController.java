package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.pojo.Role;
import com.itheima.pojo.Users;
import com.itheima.service.RoleService;
import com.itheima.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;


    /**
     * 查询所有的方法
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "pageNum", required = true, defaultValue = "1") int page, @RequestParam(name = "pageSize", required = true, defaultValue = "5") int size) throws Exception {
        List<Users> users = usersService.findAll(page, size);
        PageInfo<Users> usersPageInfo = new PageInfo<>(users);
        ModelAndView mv = new ModelAndView();

        mv.addObject("usersPageInfo", usersPageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * 根据ID查询的方法
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String id) throws Exception {
        Users byId = usersService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", byId);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/save")
    public ModelAndView save(Users users) throws Exception {
//        System.out.println("save...............");
        usersService.save(users);

        return findAll(1, 5);
    }

    @RequestMapping("/findUserByIdAndAddRole")
    public ModelAndView findUserByIdAndAddRole(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = usersService.findAllRole(id);
        mv.addObject("userId", id);
        mv.addObject("roleList", roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 给用户添加角色
     * @param
     * @return
     */
    @RequestMapping("/addRoleToUser")
    public ModelAndView addRoleToUser(@RequestParam(name = "userId") String userId, @RequestParam(name = "ids") String[] ids) throws Exception {

//        System.out.println(userId.toString() + "+++++++++++++++++++++++++++++++");
//        System.out.println(Arrays.toString(ids) + "-----------------------------");
//        System.out.println("====================================");

        usersService.addRoleToUser(userId, ids);
        return findById(userId);
    }
}

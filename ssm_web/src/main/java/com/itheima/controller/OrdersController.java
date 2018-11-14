package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.dao.OrdersDao;
import com.itheima.pojo.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

//    @RequestMapping("/findAll")
//    public ModelAndView findAll() {
//        List<Orders> orders = ordersService.findAll();
//
//
//        System.out.println(orders);
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("ordersList", orders);
//        mv.setViewName("orders-list");
//        return mv;
//    }

    @RequestMapping("/findAll")
    @PermitAll
    public ModelAndView findAll(@RequestParam(name = "pageNum", required = true, defaultValue = "1") Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer size) {
        List<Orders> orders = ordersService.findAll(page, size);
        PageInfo<Orders> pageInfo = new PageInfo<>(orders);

        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }


    @RequestMapping("/findById")
    @PermitAll
    public ModelAndView findById(String id) {
        Orders byId = ordersService.findById(id);
        //System.out.println(byId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("orders", byId);
        mv.setViewName("orders-show");
        return mv;
    }
}

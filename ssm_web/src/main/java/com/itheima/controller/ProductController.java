package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.pojo.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll")
    @PermitAll
    public ModelAndView findAll(@RequestParam(name = "pageNum", required = true, defaultValue = "1") Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll(page, size);
        //System.out.println(products);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("product-list");
        return mv;
    }


    @RequestMapping("/save")
    @RolesAllowed("ADMIN")
    public String save(Product product) {
        System.out.println(product);
        productService.save(product);

        return "redirect:findAll";
    }

    @RequestMapping("/findByIdToChange")
    public ModelAndView findByIdToChange(String id) {
        ModelAndView mv = findById(id);
        mv.setViewName("product-change");
        return mv;
    }


    @RequestMapping("/findById")
    public ModelAndView findById(String id) {
        Product byId = productService.getById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("product", byId);
        mv.setViewName("product-show");
        return mv;
    }

    @RequestMapping("/updateById")
    public ModelAndView updateById(Product product) throws Exception {
        productService.updateById(product);
        return findById(product.getId());
    }
}

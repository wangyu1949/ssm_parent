package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.pojo.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "pageNum", required = true, defaultValue = "1") int page, @RequestParam(name = "pageSize", required = true, defaultValue = "5") int size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll(page, size);
        //System.out.println(products);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("product-list");
        return mv;
    }


    @RequestMapping("/save")
    public String save(Product product) {
        System.out.println(product);
        productService.save(product);

        return "redirect:findAll";
    }

}

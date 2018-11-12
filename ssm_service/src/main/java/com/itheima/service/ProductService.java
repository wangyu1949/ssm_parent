package com.itheima.service;

import com.itheima.pojo.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll(int page, int size) throws Exception;

    void save(Product product);

    public Product getById(String id);
}

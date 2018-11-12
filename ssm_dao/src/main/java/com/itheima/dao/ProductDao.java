package com.itheima.dao;

import com.itheima.pojo.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * product数据库操作类
 */
@Repository
public interface ProductDao {

    /**
     * 查询所有的方法
     * @return
     */
    public List<Product> findAll() throws Exception;

    /**
     * 保存产品的方法
     * @param product
     */
    void save(Product product);

    /**
     * 根据productID查询product对象的方法
     * @param id
     * @return
     */
    public Product getById(String id);

    void updateById(Product product);
}

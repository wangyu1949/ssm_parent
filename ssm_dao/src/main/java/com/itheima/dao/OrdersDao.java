package com.itheima.dao;

import com.itheima.pojo.Orders;

import java.util.List;

public interface OrdersDao {

    public List<Orders> findAll();

    public Orders findById(String id);
}

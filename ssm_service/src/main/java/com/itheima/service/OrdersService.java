package com.itheima.service;

import com.itheima.pojo.Orders;

import java.util.List;

public interface OrdersService {

    public List<Orders> findAll(int page, int size);

    public Orders findById(String id);
}

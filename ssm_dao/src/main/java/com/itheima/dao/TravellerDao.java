package com.itheima.dao;

import com.itheima.pojo.Traveller;

import java.util.List;

public interface TravellerDao {

    public List<Traveller> findById(String id);
}

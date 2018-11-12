package com.itheima.dao;

import com.itheima.pojo.Items;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsDao {

    public List<Items> findAll();
}

package com.itheima.dao;

import com.itheima.pojo.SysLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLogDao {
    void save(SysLog sysLog);

    List<SysLog> findAll();

}

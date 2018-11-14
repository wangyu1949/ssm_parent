package com.itheima.service;

import com.itheima.pojo.SysLog;

import java.util.List;

public interface SysLogService {
    void save(SysLog sysLog);

    List<SysLog> findAll(Integer page, Integer size);
}

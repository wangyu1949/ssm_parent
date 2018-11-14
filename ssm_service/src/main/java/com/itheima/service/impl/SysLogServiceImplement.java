package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.SysLogDao;
import com.itheima.pojo.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImplement implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<SysLog> sysLogList = sysLogDao.findAll();
        return sysLogList;
    }
}

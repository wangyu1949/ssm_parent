package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.pojo.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "pageNum",required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer size) {
        List<SysLog> sysLogList = sysLogService.findAll(page, size);
        PageInfo<SysLog> sysLogPageInfo = new PageInfo<>(sysLogList);
        ModelAndView mv = new ModelAndView();
        mv.addObject("sysLogPageInfo", sysLogPageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }
}

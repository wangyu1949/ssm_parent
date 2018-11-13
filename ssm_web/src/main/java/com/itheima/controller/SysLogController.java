package com.itheima.controller;

import com.itheima.service.SysLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {


    private SysLogService sysLogService;
}

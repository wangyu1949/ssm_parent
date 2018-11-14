package com.itheima.controller;

import com.itheima.pojo.SysLog;
import com.itheima.pojo.Users;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date visitTime;
    private Class clazz;
    private Method method;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    @Before("execution(* com.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint jp) {
        //获取访问时间
        visitTime = new Date();

        //获取访问的类
        clazz = jp.getTarget().getClass();

        //获取访问的方法的名称
        String methodName = jp.getSignature().getName();

        //获取访问的方法
        try {
            Object[] args = jp.getArgs();
            if (args == null || args.length == 0) {
                method = clazz.getMethod(methodName);
            } else {
                Class[] argsClass = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    argsClass[i] = args[i].getClass();
                }
                method = clazz.getMethod(methodName, argsClass);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    @After("execution(* com.itheima.controller.*.*(..))")
    public void doAfter() {
        if (clazz != null && clazz != LogAop.class && method != null) {
            //获取访问者
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = user.getUsername();

            //获取访问ip
            String ip = request.getRemoteAddr();

            //获取访问路径url
            String url = "";
            //获取类的资源路径名
            RequestMapping classAnnotation = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();

                //获取访问方法的资源路径名
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                }
            }

            //获取访问时长
            long executionTime = new Date().getTime() - visitTime.getTime();

            //封装SYSLog对象
            SysLog sysLog = new SysLog();
            sysLog.setExecutionTime(executionTime);
            sysLog.setIp(ip);
            sysLog.setUrl(url);
            sysLog.setUsername(username);
            sysLog.setVisitTime(visitTime);
            sysLog.setMethod("[访问类名]" + clazz.getName() + " [访问方法名]" + method.getName());

            //保存日志
            sysLogService.save(sysLog);
        }

    }
}

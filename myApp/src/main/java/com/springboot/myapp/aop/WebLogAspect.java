package com.springboot.myapp.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/*
 * AOP日志
 */
@Aspect
@Component
public class WebLogAspect {

    private static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    //web日志
    @Pointcut("execution(public * com.springboot.myapp.web..*.*(..))")
    public void webLog(){}

    public void printLog(String str){
        logger.info(str);
    }

    @Before("webLog()")
    public void doBeforeWebLog() throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes == null){
            return ;
        }
        HttpServletRequest request = attributes.getRequest();
        if(request == null){
            return;
        }
        StringBuilder sb = new StringBuilder();
        printLog("############Request-begin###############");
        printLog("URL:"+request.getRequestURI().toString());
        printLog("HTTP_METHOD:"+request.getMethod());
        printLog("IP:"+request.getRemoteAddr());
        //heaeder信息
        Enumeration<String> headerNames = request.getHeaderNames();
        if(headerNames.hasMoreElements()){
            sb.append("HEADER ");
            while(headerNames.hasMoreElements()){
                String key = headerNames.nextElement();
                String header = request.getHeader(key);
                sb.append(key).append(":").append(header).append(",");
            }
            printLog(sb.toString());
        }

        //参数信息
        Enumeration<String> parameterNames = request.getParameterNames();
        if(parameterNames.hasMoreElements()){
            sb.setLength(0);
            sb.append("PARAMTER ");
            while (parameterNames.hasMoreElements()){
                String name = parameterNames.nextElement();
                String parameter = request.getParameter(name);
                sb.append(name).append(":").append(parameter).append(",");
            }
            printLog(sb.toString());
        }
        printLog("############Request-end#################");
    }

    @AfterReturning(pointcut = "webLog()",returning = "ret")
    public void doAfterReturning(Object ret){
        printLog("Response:"+ret.toString());
    }

    //service日志
    @Pointcut("execution(public * com.springboot.myapp.service..*.*(..))")
    public void serviceLog(){}

    @Before("serviceLog()")
    public void doBeforeServiceLog(){
        printLog("Service log");
    }


    //dao日志
}

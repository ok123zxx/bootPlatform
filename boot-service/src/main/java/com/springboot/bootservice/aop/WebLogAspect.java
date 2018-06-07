package com.springboot.bootservice.aop;

import com.springboot.base.constants.RedisConstants;
import com.springboot.base.utils.JedisUtils;
import com.springboot.base.utils.LogUtils;
import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.DelegatingServletOutputStream;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.Enumeration;

/*
 * AOP日志
 */
@Aspect
@Component
public class WebLogAspect {

    private static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    public static boolean TEST = false;

    //web日志
    @Pointcut("execution(public * com.springboot.bootservice.web..*.*(..))")
    public void webLog(){}

    public static void printLog(String str){
        if(TEST){
            logger.warn(str);
        }else{
            Jedis jedis = null;
            try {
                jedis = JedisUtils.getResource();
                Long lpush = jedis.lpush(RedisConstants.ASPECT_LOG_QUEUE.value,str+"\n");
            }catch (Exception e) {
                LogUtils.warnPrint(str);
            }finally {
                JedisUtils.returnResource(jedis);
            }
        }
    }

    public static void printRequest(HttpServletRequest request){
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
            printLog(sb.substring(0,sb.length()-1));
        }
        printLog("############Request-end#################");
    }

    public static void printResponse(HttpServletResponse response){
        StringBuilder sb = new StringBuilder();
        printLog("############Response-begin###############");
        printLog("Status:"+response.getStatus());
        printLog("ContentType:"+response.getContentType());
        printLog("CharacterEncoding:"+response.getCharacterEncoding());
        printLog("BufferSize:"+response.getBufferSize());
        Collection<String> headerNames = response.getHeaderNames();
        if(CollectionUtils.isNotEmpty(headerNames)){
            sb.append("HEADER ");
            for (String headerName : headerNames) {
                String header = response.getHeader(headerName);
                sb.append(headerName).append(":").append(header).append(",");
            }
            printLog(sb.substring(0,sb.length()-1));
        }
        //输出response content
        if(TEST){
            try {
                ServletOutputStream outputStream = response.getOutputStream();
                DelegatingServletOutputStream delegatingServletOutputStream = (DelegatingServletOutputStream) outputStream;
                ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) delegatingServletOutputStream.getTargetStream();
                byte[] bytes = byteArrayOutputStream.toByteArray();
                String str = new String(bytes);
                printLog("CONTENT:"+str);
            } catch (Exception e) {
                LogUtils.errorPrint("",e);
            }
        }
        printLog("############Response-end#################");
    }

    @Before("webLog()")
    public void doBeforeWebLog(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes == null){
            return ;
        }
        HttpServletRequest request = attributes.getRequest();
        if(request != null){
            printRequest(request);
        }
    }

    @After("webLog()")
    public void doAfterWebLog(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes == null)
            return ;
        HttpServletResponse response = attributes.getResponse();
        if(response != null){
            printResponse(response);
        }
    }

    @AfterReturning(pointcut = "webLog()",returning = "ret")
    public void doAfterReturning(Object ret){
        if(ret != null)
            printLog("Response:"+ret.toString());
    }

    //service日志
    @Pointcut("execution(public * com.springboot.bootservice.service..*.*(..))")
    public void serviceLog(){}

    @Before("serviceLog()")
    public void doBeforeServiceLog(){
        printLog("Service log");
    }


    //dao日志
}

package com.springboot.bootSchedule.schedule;

import com.springboot.base.constants.RedisConstants;
import com.springboot.base.context.SpringContextHolder;
import com.springboot.base.utils.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by zengJian on 2018/4/6<br>
 * <br>
 */
@Component
public class AspectLogTask {

    @Value("${apsect-log.filePath}")
    private String logFilePath;

    private String logFileName;

    private String logTime;

    public String getFileName(){
        if(StringUtils.isBlank(logFileName)){
            AspectLogTask bean = SpringContextHolder.getBean(AspectLogTask.class);
            bean.updateFileName();
        }
        if(StringUtils.isBlank(logFileName)){
            logFileName = "exception";
        }
        return "/aspectLog."+logFileName+".log";
    }

    public String getLogTime(){
        if(StringUtils.isBlank(logTime)){
            AspectLogTask bean = SpringContextHolder.getBean(AspectLogTask.class);
            bean.updateFileName();
        }
        if(StringUtils.isBlank(logTime)){
            logTime = "exception";
        }
        return logTime+":";
    }

    @Scheduled(fixedDelay = 1000)
    public void flushLogInFile(){
        try{
            String key = RedisConstants.ASPECT_LOG_QUEUE.value;
            int stdLength = 10;
            Long length = JedisUtils.llen(key);
            if(length < stdLength)
                return ;

            LogUtils.warnPrint("==================开始 刷入aspectLog");
            LogUtils.warnPrint("aspect日志元素总长度："+length);
            while(length > 0) {
                try {
                    List<String> brpop = JedisUtils.brpop(300,key);
                    if(CollectionUtils.isNotEmpty(brpop)){
                        String value = brpop.get(1);
                        String fileName = logFilePath+getFileName();
                        value = getLogTime() + value;
                        FileUtils.writeToFile(fileName,value,true);
                        LogUtils.warnPrint("刷入成功");
                    }
                }catch (Exception e) {
                    LogUtils.errorPrint("",e);
                }finally {
                    length--;
                }
            }
            LogUtils.warnPrint("==================结束 刷入aspectLog");
        }catch (Exception e){
            LogUtils.errorPrint("",e);
        }
    }

    @Scheduled(fixedDelay = 1000*60)
    public void updateFileName(){
        try{
            String date = DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
            logTime = date;
            logFileName = date.split(" ")[0];
        }catch (Exception e){
            LogUtils.errorPrint("",e);
        }
    }
}

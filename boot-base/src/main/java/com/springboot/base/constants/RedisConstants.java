package com.springboot.base.constants;

/**
 * Created by zengJian on 2018/4/8<br>
 * <br>
 */
public enum RedisConstants{
    ASPECT_LOG_QUEUE("项目切面日志队列，从内存周期性刷到文件中","ASPECT_LOG_QUEUE");

    public String key;
    public String value;

    RedisConstants(String key,String value){
        this.key = key;
        this.value = value;
    }
}

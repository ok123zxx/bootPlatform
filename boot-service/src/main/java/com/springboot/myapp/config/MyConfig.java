package com.springboot.myapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by zengJian on 2018/4/9<br>
 * <br>
 */
@Component
@PropertySource(value = "classpath:app-config.properties")
public class MyConfig {

    @Value("${tang.seng}")
    private String seng;


    public String getSeng() {
        return seng;
    }

    public void setSeng(String seng) {
        this.seng = seng;
    }
}

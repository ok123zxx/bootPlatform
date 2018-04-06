package com.springboot.bootSchedule.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by zengJian on 2018/4/6<br>
 * <br>
 */
@Component
public class AspectLogTask {

    @Scheduled()
    public void flushLogInFile(){

    }


}

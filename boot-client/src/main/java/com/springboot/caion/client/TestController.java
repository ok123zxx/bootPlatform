package com.springboot.caion.client;

import com.springboot.caion.client.remoteController.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zengJian on 2018/5/4<br>
 * <br>
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    HelloRemote helloRemote;

    @RequestMapping()
    public String test(){
//        return helloRemote.index();
        return helloRemote.hello(" test "+System.currentTimeMillis());
    }
}

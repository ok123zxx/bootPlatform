package com.springboot.caion.client.remoteController;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "boot-service")
public interface HelloRemote {
    @RequestMapping(value = "/base/echo")
    String hello(@RequestParam(value = "name") String name);
}
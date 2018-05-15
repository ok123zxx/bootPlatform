package com.springboot.caion.client.remoteController;

import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name= "boot-service")
public interface HelloRemote {
    @RequestMapping(value = "/base/echo",method = RequestMethod.GET)
    String hello(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/")
    String index();

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /users/list")//这个注解是啥意思？
//    @RequestHeader//这个注解是啥意思？

    Object foo(@PathVariable("sas") String a);
}
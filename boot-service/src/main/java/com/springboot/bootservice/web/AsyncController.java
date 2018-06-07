package com.springboot.bootservice.web;

import com.springboot.bootservice.service.SpringBootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private SpringBootService springBootService;

    @RequestMapping("test")
    public Object doAsync() throws Exception{
        springBootService.getAsyncname();
        return "success";
    }
}

package com.springboot.myapp.web;

import com.springboot.myapp.service.SpringBootService;
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

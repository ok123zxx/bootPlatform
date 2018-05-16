package com.springboot.myapp.web;

import com.springboot.base.utils.LogUtils;
import com.springboot.myapp.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zengJian on 2018/5/16<br>
 * <br>
 */
@RestController
@RequestMapping(value = "/store")
public class BuyStoreController {

    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/buy")
    public Object buy(String storeId,String custName,Long num){
        storeService.consume(storeId,custName,num);
        return "success";
    }

    @RequestMapping(value = "/create")
    public Object createStore(){
        storeService.createStore(100L);
        return "success";
    }

}

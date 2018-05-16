package com.springboot.caion.client.test;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by zengJian on 2018/5/10<br>
 * <br>
 */
public class C {

    public static void main(String[] args) {
        new C().print(32);
        new C().print(new Double(32));

//        RateLimiter limiter = RateLimiter.create(1);
//        while(true){
//            System.out.println(limiter.acquire(2));
//        }
    }

    void print(Double d){
        System.out.println("1");
    }
    void print(double d){
        System.out.println("2");
    }
}

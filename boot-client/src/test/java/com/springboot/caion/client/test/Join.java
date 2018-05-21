package com.springboot.caion.client.test;

/**
 * Created by zengJian on 2018/5/21<br>
 * <br>
 */
public class Join {

    public static void main(String[] args) throws Exception{

        Thread a = new Thread(()->{
            System.out.println("thread-1");
        });
        Thread b = new Thread(()->{
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread-2");
        });

        a.start();
        b.start();

        a.join();
//        b.join();
        System.out.println("thread-0");


    }
}

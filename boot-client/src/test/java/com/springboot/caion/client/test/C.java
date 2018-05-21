package com.springboot.caion.client.test;

/**
 * Created by zengJian on 2018/5/10<br>
 * <br>
 */
public class C {

    public static void main(String[] args) {
        String str = "dsjdasoijda";
        System.out.println(str.intern());
        MyEnum.INSTANCE.get();
    }

    void print(Double d){
        System.out.println("1");
    }
    void print(double d){
        System.out.println("2");
    }
}

package com.springboot.caion.client.test;

/**
 * Created by zengJian on 2018/5/18<br>
 * <br>
 */
public enum MyEnum {
    INSTANCE(new A());

    public A get(){
        return a;
    }
    private MyEnum(A a){
        this.a = a;
    }
    private A a;
}

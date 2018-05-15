package com.springboot.caion.client.test;

/**
 * Created by zengJian on 2018/5/10<br>
 * <br>
 */
public class B {

    int i = 1;
    public B(){
        this(2);
    }

    public B(int i ){
        this.i = i;
    }

    void print(){
        System.out.println(i);
    }

    public static void main(String[] args) {
        new Sub().print();
    }

   static class Sub extends  B{
        public Sub(){

        }
        public Sub(int i){
            super(i+3);
        }
    }
}

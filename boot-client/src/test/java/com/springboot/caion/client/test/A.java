package com.springboot.caion.client.test;

import java.util.Random;

/**
 * Created by zengJian on 2018/5/9<br>
 * <br>
 */
public class A {
    private int c = 0;

    public static void main(String[] args) {
        String str1 = "test";
        String str2 = new String("test");
        String str3 = str1;
        String str4 = str3;
        str1 = str2;
        str2 = str4;
        System.out.println(str1 == str2);
        System.out.println(str2 == str3);
    }

    public static void run(){
        A a = new A();
        for (int i = 0; i < 4; i++) {
            try {
                a.da(i);
            } catch (MyExc myExc) {

            }
        }
        System.out.println(a.c);
    }

    double da(int i) throws MyExc{
        double result ;
        try{
            result =    2/i;
            if(result >1){
                return result;
            }
        }catch (RuntimeException e){
            throw new MyExc();
        }finally {
            c++;
        }
        return result;
    }

    class MyExc extends  Exception{}
}

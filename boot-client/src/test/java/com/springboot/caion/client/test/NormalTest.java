package com.springboot.caion.client.test;


import org.apache.ibatis.annotations.Param;
import sun.net.www.http.HttpClient;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zengJian on 2018/5/7<br>
 * <br>
 */
public class NormalTest {
    final static String[] str = new String[1];
    static ExecutorService executorService = Executors.newSingleThreadExecutor();
    public static void main(String[] args) throws  Exception {
        System.out.println(fun0(4));
        System.out.println(fun0(0));
        System.out.println(fun0(-3));
        System.out.println(fun0(-2));

    }

    static int fun0(int i){
        try{
            return ((Ex1)fun1(i)).mul(5);
        }catch (Ex2 e){
            return e.mul(10);
        }catch (Ex1 e){
            return  e.add(10);
        }
        catch (Exception e){

        }
        return -1;
    }

    static Exception fun1(int i)throws  Exception{
        Exception[] e = new Exception[]{
            null,new Ex1(),new Ex2(),new Ex3()
        };
        if(i>0) throw e[i];
        if(i<0) e[0]=e[-i];
        return e[0];
    }

    static  class Ex1 extends Exception{
        int add(int i){return i+2;}
        int mul(int i){return i*2;}
    }

    static  class Ex2 extends Ex1{
        int add(int i){return i+3;}
        int mul(int i){return i*3;}
    }


    static  class Ex3 extends Ex1{
        int mul(int i){return i*4;}
    }
}

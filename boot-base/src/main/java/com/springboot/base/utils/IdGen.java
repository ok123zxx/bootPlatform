package com.springboot.base.utils;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 */
@Service
@Lazy(false)
public class IdGen{

   private static SecureRandom random = new SecureRandom();

   /**
    * 获取一个5位数的随机数[10000,99999]
    */
   public static String randomIdNum(){
      Random random = new Random(System.currentTimeMillis());
      int max=99999;
      int min=10000;
      int s = random.nextInt(max)%(max-min+1) + min;
      return String.valueOf(s);
   }

   /**
    * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
    */
   public static String uuid() {
      return UUID.randomUUID().toString().replaceAll("-", "");
   }
   
   /**
    * 使用SecureRandom随机生成Long. 
    */
   public static long randomLong() {
      return Math.abs(random.nextLong());
   }

   /**
    * 基于Base62编码的SecureRandom随机生成bytes.
    */
   public static String randomBase62(int length) {
      byte[] randomBytes = new byte[length];
      random.nextBytes(randomBytes);
      return Encodes.encodeBase62(randomBytes);
   }

}
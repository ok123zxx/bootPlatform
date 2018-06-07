package com.springboot.bootservice.exception;

/**
 * Created by zengJian on 2018/5/16<br>
 * <br>
 *     业务错误枚举
 */
public class ErrorEnum {

    public static final ErrorEnum SALEOUT = new ErrorEnum("1000","库存不足");

    private String key;
    private String value;

    private ErrorEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

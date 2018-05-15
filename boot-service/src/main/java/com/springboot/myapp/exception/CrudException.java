package com.springboot.myapp.exception;

/**
 * Created by zengJian on 2018/5/15<br>
 * <br>
 */
public class CrudException extends BaseException{

    public CrudException() {

    }

    public CrudException(String msg) {
        super(msg);
    }
}

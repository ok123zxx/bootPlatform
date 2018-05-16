package com.springboot.myapp.exception;

/**
 * Created by zengJian on 2018/5/16<br>
 * <br>
 */
public class BusException extends BaseException {
    private ErrorEnum error;

    public BusException(ErrorEnum error){
        this.error = error;
    }

}

package com.springboot.bootservice.service;

import com.springboot.bootservice.exception.CrudException;

/**
 * Created by zengJian on 2018/5/15<br>
 * <br>
 */
public abstract class CrudService {

    public void validSave(int save,String msg){
        if(save != 1){
            throw new CrudException(msg);
        }
    }

    public void validSave(int save){
        if(save != 1){
            throw new CrudException();
        }
    }

}

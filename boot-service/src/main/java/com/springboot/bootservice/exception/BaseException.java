package com.springboot.bootservice.exception;

public class BaseException extends RuntimeException{

	private static final long serialVersionUID = 5615198878907989788L;

	public BaseException(){

	}

	public BaseException(String msg){
		super(msg);
	}
}

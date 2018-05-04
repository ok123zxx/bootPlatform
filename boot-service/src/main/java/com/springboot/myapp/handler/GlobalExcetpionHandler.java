package com.springboot.myapp.handler;

import com.springboot.base.utils.LogUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.myapp.exception.BaseException;

/**
 * @author caion
 * 全局异常捕获
 */
@ControllerAdvice//捕获Controller层，如果异常抛出点在Service层，那么不会起作用 TODO
public class GlobalExcetpionHandler {

	@ResponseBody
	@ExceptionHandler(RuntimeException.class)//运行时异常
	public Object runTimeexceptionHandler(RuntimeException e) {
		LogUtils.errorPrint("",e);
		return "runTime error handler";
	}

	@ResponseBody
	@ExceptionHandler(BaseException.class)//业务异常
	public Object baseExceptionHandler(BaseException e) {
		LogUtils.errorPrint("",e);
		return "business error";
	}

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Object runTimeexceptionHandler(Throwable e) {
        LogUtils.errorPrint("",e);
        return "Throwable handler";
    }
}

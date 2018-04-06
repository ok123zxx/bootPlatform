package com.springboot.myapp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 字典工具类
 */
public class LogUtils {
	
	private static ExecutorService threadPool = Executors.newSingleThreadExecutor();

	public static void warnPrint(String str) {
		print(2, str, null);
	}
	public static void infoPrint(String str) {
		print(1, str, null);
	}
	public static void errorPrint(String str) {
		print(3, str, null);
	}
	public static void warnPrint(String str,Throwable t) {
		print(2, str, t);
	}
	public static void infoPrint(String str,Throwable t) {
		print(1, str, t);
	}
	public static void errorPrint(String str,Throwable t) {
		print(3, str, t);
	}
	/**
	 * 快捷打印到日志文件
	 */
	public static void print(int type,String str,Throwable t) {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		threadPool.execute(()->{
			Logger log = LoggerFactory.getLogger(stacks[3].getClassName());
			switch (type) {
			case 1://info
				log.info(str,t);
				break;
			case 2://warn
				log.warn(str,t);
				break;
			case 3://error
				log.error(str,t);
				break;
			default:
				log.debug(str,t);
				break;
			}
		});
	}
}

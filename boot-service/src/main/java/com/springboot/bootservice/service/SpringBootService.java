package com.springboot.bootservice.service;

import com.springboot.base.utils.LogUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SpringBootService {

	public String getName() {
		return "service get name caion";
	}

	@Async//("taskExecutor")//可以指定名taskExcutor
	public String getAsyncname() throws Exception{
		LogUtils.infoPrint("finish job!");
		return "service get name caion";
	}

}

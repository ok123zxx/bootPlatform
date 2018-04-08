package com.springboot.myapp.web;

import com.springboot.base.utils.JedisUtils;
import com.springboot.base.utils.Underline2Camel;
import com.springboot.myapp.exception.BaseException;
import com.springboot.myapp.service.SpringBootService;
import com.springboot.myapp.service.StoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/base")
public class SpringBootController {

	@Autowired
	private SpringBootService bootService;

	@Autowired
	private StoreService storeService;

	/*
	 * 驼峰转下划线
	 */
	@RequestMapping("/camel2Undeline")
	public Object camel2Undeline(String str, HttpServletRequest request){
        if(StringUtils.isBlank(str) || str.length() > 500){
            return null;
        }
        return Underline2Camel.camel2Underline(str);
	}

	/*
	 * 下划线转驼峰
	 */
	@RequestMapping("/undeline2Camel")
	public Object undeline2Camel(String str,Boolean small){
        if(StringUtils.isBlank(str) || str.length() > 500){
            return null;
        }
        return Underline2Camel.underline2Camel(str,small);
	}

	@RequestMapping(value = "/getName")
	public String getName() {
		return bootService.getName();
	}
	
	@RequestMapping(value = "/getException")
	public String getException() {
		if(true) {
			throw new BaseException();
		}
		return "如果你看到这条信息就炸了";
	}

	@RequestMapping(value = "/redis")
	public String redisTest(String key){
		if("caion".equals(key)){
			JedisUtils.set(key,"zzzxxx",100);
			return JedisUtils.get(key);
		}
		return "fail";
	}


//	@RequestMapping(value = "/createStore")
//	public Object createStore(String key,String value){
//		return storeService.createStore(key,value);
//	}

	@RequestMapping(value = "/createByMapper")
	public Object createStoreByMapper(String key,String value){
		return storeService.createByMapper(key, value);
	}

//	@ResponseBody
//	@RequestMapping(value = "/findByMapper")
//	public Object findByMapper(String id){
//	    return storeService.findStore(id);
//    }




}

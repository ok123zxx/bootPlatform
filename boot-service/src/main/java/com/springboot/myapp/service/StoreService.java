package com.springboot.myapp.service;

import com.springboot.myapp.entity.Store;
import com.springboot.myapp.exception.BaseException;
import com.springboot.myapp.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class StoreService {

//	@Autowired
//	private StoreDao storeDao;//jpa

	@Autowired
	private StoreMapper storeMapper;//mybatis

//	public Store createStore(String key,String value){
//		Store store = new Store();
//		store.setId(UUID.randomUUID().toString());
//		store.setKeyT(key);
//		store.setVal(value);
//		storeDao.save(store);
//		return store;
//	}
//
//	public Store findStore(String id){
//        Store result = storeDao.findOne(id);
//        return result;
//	}

	@Transactional
	public Store createByMapper(String key,String value){
		Store store = new Store();
		store.setId(UUID.randomUUID().toString());
		store.setKeyT(key);
		store.setVal(value);
		storeMapper.insert(store);
		if(true)throw new BaseException();
		return store;
	}


}

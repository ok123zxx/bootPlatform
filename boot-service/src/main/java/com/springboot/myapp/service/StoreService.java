package com.springboot.myapp.service;

import com.springboot.base.utils.IdGen;
import com.springboot.myapp.entity.Store;
import com.springboot.myapp.entity.StoreOrder;
import com.springboot.myapp.exception.BaseException;
import com.springboot.myapp.mapper.StoreMapper;
import com.springboot.myapp.mapper.StoreOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class StoreService extends CrudService{

	@Autowired
	private StoreMapper storeMapper;

	@Autowired
	private StoreOrderMapper storeOrderMapper;

	/*
	 * 创建商品
	 */
	@Transactional
	public Store createStore(Long num){
		Store store = new Store();
		store.setId(IdGen.uuid());
		store.setNum(num);
		store.setLockVersion(0L);
		store.setCreateDate(new Date());
		store.setUpdateDate(new Date());
		validSave(storeMapper.insert(store),"插入库存失败");
		return store;
	}

	/*
	 * 更新库存
	 */
	@Transactional
	public void subtractStore(String id,Long num){
		Store store = storeMapper.getById(id);
		Long lockVersion = store.getLockVersion();
		validSave(storeMapper.updateStoreNum(id,num,lockVersion),"并发更新库存失败");
	}

	/*
	 * 创建订单
	 */
	@Transactional
	public StoreOrder createStoreOrder(String custName){
		StoreOrder storeOrder = new StoreOrder();
		storeOrder.setId(IdGen.uuid());
		storeOrder.setCustName(custName);
		storeOrder.setCreateDate(new Date());
		storeOrder.setUpdateDate(new Date());
		validSave(storeOrderMapper.insert(storeOrder),"插入订单失败");
		return storeOrder;
	}

}

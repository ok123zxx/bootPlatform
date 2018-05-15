package com.springboot.myapp.entity;

/*
 * 库存表
 */
public class Store extends BaseEntity{

	private Long num;
	private Long lockVersion;

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Long getLockVersion() {
		return lockVersion;
	}

	public void setLockVersion(Long lockVersion) {
		this.lockVersion = lockVersion;
	}
}

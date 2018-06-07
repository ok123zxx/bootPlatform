package com.springboot.bootservice.entity;

/**
 * Created by zengJian on 2018/5/15<br>
 * <br>
 * 订单表
 */
public class StoreOrder extends BaseEntity{

    private String storeId;
    private String custName;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
}

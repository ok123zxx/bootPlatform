package com.springboot.myapp.entity;

/**
 * Created by zengJian on 2018/5/15<br>
 * <br>
 * 订单表
 */
public class StoreOrder extends BaseEntity{

    private String custName;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
}

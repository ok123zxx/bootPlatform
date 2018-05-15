package com.springboot.myapp.mapper;

import com.springboot.myapp.entity.StoreOrder;
import org.apache.ibatis.annotations.Insert;

public interface StoreOrderMapper {

    @Insert("insert into store_order(id,cust_name,update_date,create_date) values(#{id},#{custName},#{updateDate},#{createDate})")
    int insert(StoreOrder storeOrder);
}

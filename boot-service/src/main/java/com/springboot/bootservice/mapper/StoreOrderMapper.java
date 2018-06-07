package com.springboot.bootservice.mapper;

import com.springboot.bootservice.entity.StoreOrder;
import org.apache.ibatis.annotations.Insert;

public interface StoreOrderMapper {

    @Insert("insert into store_order(id,store_id,cust_name,update_date,create_date) values(#{id},#{storeId},#{custName},#{updateDate},#{createDate})")
    int insert(StoreOrder storeOrder);
}

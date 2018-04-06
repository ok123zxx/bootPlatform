package com.springboot.myapp.mapper;

import com.springboot.myapp.entity.Store;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface StoreMapper {

    @Select("select * from store where id = #{id}")
    Store getById(String id);

    @Insert("insert into store(id,keyT,val) values(#{id},#{keyT},#{val})")
    int insert(Store store);

}

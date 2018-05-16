package com.springboot.myapp.mapper;

import com.springboot.myapp.entity.Store;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface StoreMapper {

    @Select("select id,num,lock_version as lockVersion from store where id = #{id}")
    Store getById(String id);

    @Insert("insert into store(id,num,lock_version,update_date,create_date) values(#{id},#{num},#{lockVersion},#{updateDate},#{createDate})")
    int insert(Store store);

    @Update("update store set num = #{num},lock_version = lock_version + 1 where id = #{id} and lock_version = #{lockVersion}")
    int updateStoreNumWithLock(@Param("id")String id, @Param("num") Long num, @Param("lockVersion") Long lockVersion);

    @Update("update store set num = #{num} where id = #{id}")
    int updateStoreNum(@Param("id") String id, @Param("num") Long num);


}

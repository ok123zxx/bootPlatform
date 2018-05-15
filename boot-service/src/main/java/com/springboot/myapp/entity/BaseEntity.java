package com.springboot.myapp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zengJian on 2018/5/15<br>
 * <br>
 */
public class BaseEntity implements Serializable{
    private String id;
    private Date updateDate;
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

package com.shn.gmall.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;


/**
 * @author sss
 */
@Table(name = "test_table")
public class TestTableVO implements Serializable {


    private static final long serialVersionUID = -7715131972905525358L;
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Transient
    private String userId;
    @Column
    private String name;
    @Column
    private Date createTime;
    @Column
    private String createUserId;
    @Column
    private Date  updateTime;
    @Column
    private String updateUserId;
    @Column
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date  getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date  createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date  getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date  updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}

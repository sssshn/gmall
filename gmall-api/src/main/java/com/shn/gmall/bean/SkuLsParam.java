package com.shn.gmall.bean;

import java.io.Serializable;

/**
 * @author sss
 */
public class SkuLsParam implements Serializable {

    private static final long serialVersionUID = -1869562385434813528L;
    private String catalog3Id;

    private String [] valueId;

    private String keyword;

    private int pageNo = 1;
    private int pageSize = 20;

    public String getCatalog3Id() {
        return catalog3Id;
    }

    public void setCatalog3Id(String catalog3Id) {
        this.catalog3Id = catalog3Id;
    }

    public String[] getValueId() {
        return valueId;
    }

    public void setValueId(String[] valueId) {
        this.valueId = valueId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

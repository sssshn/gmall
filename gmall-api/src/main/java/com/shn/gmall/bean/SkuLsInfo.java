package com.shn.gmall.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class SkuLsInfo implements Serializable {
    private static final long serialVersionUID = -8081963530377793386L;

    private String id;
    private BigDecimal price;
    private String skuName;
    private String skuDesc;
    private String catalog3Id;
    private String skuDefaultImg;
    private Long hotScore = 0L;
    private List<SkuLsAttrValue> skuLsAttrValueList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuDesc() {
        return skuDesc;
    }

    public void setSkuDesc(String skuDesc) {
        this.skuDesc = skuDesc;
    }

    public String getCatalog3Id() {
        return catalog3Id;
    }

    public void setCatalog3Id(String catalog3Id) {
        this.catalog3Id = catalog3Id;
    }

    public String getSkuDefaultImg() {
        return skuDefaultImg;
    }

    public void setSkuDefaultImg(String skuDefaultImg) {
        this.skuDefaultImg = skuDefaultImg;
    }

    public Long getHotScore() {
        return hotScore;
    }

    public void setHotScore(Long hotScore) {
        this.hotScore = hotScore;
    }

    public List<SkuLsAttrValue> getSkuLsAttrValueList() {
        return skuLsAttrValueList;
    }

    public void setSkuLsAttrValueList(List<SkuLsAttrValue> skuLsAttrValueList) {
        this.skuLsAttrValueList = skuLsAttrValueList;
    }
}

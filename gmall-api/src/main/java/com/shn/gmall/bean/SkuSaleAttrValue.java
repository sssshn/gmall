package com.shn.gmall.bean;


import java.io.Serializable;

/**
 * @author sss
 */
public class SkuSaleAttrValue implements Serializable {

  private static final long serialVersionUID = 1980017261318328483L;
  private Integer id;
  private Integer skuId;
  private Integer saleAttrId;
  private Integer saleAttrValueId;
  private String saleAttrName;
  private String saleAttrValueName;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getSkuId() {
    return skuId;
  }

  public void setSkuId(Integer skuId) {
    this.skuId = skuId;
  }


  public Integer getSaleAttrId() {
    return saleAttrId;
  }

  public void setSaleAttrId(Integer saleAttrId) {
    this.saleAttrId = saleAttrId;
  }


  public Integer getSaleAttrValueId() {
    return saleAttrValueId;
  }

  public void setSaleAttrValueId(Integer saleAttrValueId) {
    this.saleAttrValueId = saleAttrValueId;
  }


  public String getSaleAttrName() {
    return saleAttrName;
  }

  public void setSaleAttrName(String saleAttrName) {
    this.saleAttrName = saleAttrName;
  }


  public String getSaleAttrValueName() {
    return saleAttrValueName;
  }

  public void setSaleAttrValueName(String saleAttrValueName) {
    this.saleAttrValueName = saleAttrValueName;
  }

}

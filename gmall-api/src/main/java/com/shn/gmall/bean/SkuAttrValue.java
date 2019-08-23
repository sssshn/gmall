package com.shn.gmall.bean;


import java.io.Serializable;

/**
 * @author sss
 */
public class SkuAttrValue implements Serializable {

  private static final long serialVersionUID = -6599246435474258107L;
  private Integer id;
  private Integer attrId;
  private Integer valueId;
  private Integer skuId;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getAttrId() {
    return attrId;
  }

  public void setAttrId(Integer attrId) {
    this.attrId = attrId;
  }


  public Integer getValueId() {
    return valueId;
  }

  public void setValueId(Integer valueId) {
    this.valueId = valueId;
  }


  public Integer getSkuId() {
    return skuId;
  }

  public void setSkuId(Integer skuId) {
    this.skuId = skuId;
  }

}

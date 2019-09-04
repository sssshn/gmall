package com.shn.gmall.bean;


import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author sss
 */
public class SpuSaleAttrValue implements Serializable {

  private static final long serialVersionUID = -6358032268020213283L;
  @Id
  private String id;
  private String spuId;
  private String saleAttrId;
  private String saleAttrValueName;

  @Transient
  private String isChecked;

  public String getIsChecked() {
    return isChecked;
  }

  public void setIsChecked(String isChecked) {
    this.isChecked = isChecked;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getSpuId() {
    return spuId;
  }

  public void setSpuId(String spuId) {
    this.spuId = spuId;
  }


  public String getSaleAttrId() {
    return saleAttrId;
  }

  public void setSaleAttrId(String saleAttrId) {
    this.saleAttrId = saleAttrId;
  }


  public String getSaleAttrValueName() {
    return saleAttrValueName;
  }

  public void setSaleAttrValueName(String saleAttrValueName) {
    this.saleAttrValueName = saleAttrValueName;
  }

}

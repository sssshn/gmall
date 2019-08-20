package com.shn.gmall.bean;


import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @author sss
 */
public class SpuSaleAttr implements Serializable {

  private static final long serialVersionUID = -6790619545875728892L;
  @Id
  private String id;
  private String spuId;
  private String saleAttrId;
  private String saleAttrName;

  @Transient
  private List<SpuSaleAttrValue> spuSaleAttrValueList;

  public List<SpuSaleAttrValue> getSpuSaleAttrValueList() {
    return spuSaleAttrValueList;
  }

  public void setSpuSaleAttrValueList(List<SpuSaleAttrValue> spuSaleAttrValueList) {
    this.spuSaleAttrValueList = spuSaleAttrValueList;
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


  public String getSaleAttrName() {
    return saleAttrName;
  }

  public void setSaleAttrName(String saleAttrName) {
    this.saleAttrName = saleAttrName;
  }

}

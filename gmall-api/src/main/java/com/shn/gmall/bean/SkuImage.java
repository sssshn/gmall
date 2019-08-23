package com.shn.gmall.bean;


import java.io.Serializable;

public class SkuImage implements Serializable {

  private static final long serialVersionUID = -5607762008424228353L;
  private Integer id;
  private Integer skuId;
  private String imgName;
  private String imgUrl;
  private Integer spuImgId;
  private String isDefault;


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


  public String getImgName() {
    return imgName;
  }

  public void setImgName(String imgName) {
    this.imgName = imgName;
  }


  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }


  public Integer getSpuImgId() {
    return spuImgId;
  }

  public void setSpuImgId(Integer spuImgId) {
    this.spuImgId = spuImgId;
  }


  public String getIsDefault() {
    return isDefault;
  }

  public void setIsDefault(String isDefault) {
    this.isDefault = isDefault;
  }

}

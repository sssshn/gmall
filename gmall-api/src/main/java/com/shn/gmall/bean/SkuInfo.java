package com.shn.gmall.bean;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @author sss
 */
public class SkuInfo implements Serializable {

  private static final long serialVersionUID = -1428445172853233301L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String spuId;
  @Column(name="price")
  private Double price;
  private String skuName;
  private String skuDesc;
  @Column(name="weight")
  private Double weight;
  private String tmId;
  private String catalog3Id;
  private String skuDefaultImg;

  @Transient
  private List<SkuAttrValue> skuAttrValueList;
  @Transient
  private List<SkuSaleAttrValue> skuSaleAttrValueList;
  @Transient
  private List<SkuImage> skuImageList;

  public List<SkuImage> getSkuImageList() {
        return skuImageList;
  }

  public void setSkuImageList(List<SkuImage> skuImageList) {
        this.skuImageList = skuImageList;
  }



  public List<SkuSaleAttrValue> getSkuSaleAttrValueList() {
    return skuSaleAttrValueList;
  }

  public void setSkuSaleAttrValueList(List<SkuSaleAttrValue> skuSaleAttrValueList) {
    this.skuSaleAttrValueList = skuSaleAttrValueList;
  }

  public List<SkuAttrValue> getSkuAttrValueList() {
        return skuAttrValueList;
    }

  public void setSkuAttrValueList(List<SkuAttrValue> skuAttrValueList) {
        this.skuAttrValueList = skuAttrValueList;
    }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getSpuId() {
    return spuId;
  }

  public void setSpuId(String spuId) {
    this.spuId = spuId;
  }


  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
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


  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }


  public String getTmId() {
    return tmId;
  }

  public void setTmId(String tmId) {
    this.tmId = tmId;
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

}

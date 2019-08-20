package com.shn.gmall.bean;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @author sss
 */
public class SpuInfo implements Serializable {


  private static final long serialVersionUID = -5311063383453912275L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;
  private String spuName;
  private String description;
  private String catalog3Id;
  private String tmId;

  @Transient
  private List<SpuSaleAttr> spuSaleAttrList;

  public List<SpuSaleAttr> getSpuSaleAttrList() {
    return spuSaleAttrList;
  }

  public void setSpuSaleAttrList(List<SpuSaleAttr> spuSaleAttrList) {
    this.spuSaleAttrList = spuSaleAttrList;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getSpuName() {
    return spuName;
  }

  public void setSpuName(String spuName) {
    this.spuName = spuName;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getCatalog3Id() {
    return catalog3Id;
  }

  public void setCatalog3Id(String catalog3Id) {
    this.catalog3Id = catalog3Id;
  }


  public String getTmId() {
    return tmId;
  }

  public void setTmId(String tmId) {
    this.tmId = tmId;
  }

}
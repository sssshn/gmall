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
public class BaseAttrInfo implements Serializable {

  private static final long serialVersionUID = -6600683468842381195L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;
  private String attrName;
  private String catalog3Id;
  private String isEnabled;

  @Transient
  private List<BaseAttrValue> attrValueList;

  public List<BaseAttrValue> getAttrValueList() {
    return attrValueList;
  }

  public void setAttrValueList(List<BaseAttrValue> attrValueList) {
    this.attrValueList = attrValueList;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getAttrName() {
    return attrName;
  }

  public void setAttrName(String attrName) {
    this.attrName = attrName;
  }


  public String getCatalog3Id() {
    return catalog3Id;
  }

  public void setCatalog3Id(String catalog3Id) {
    this.catalog3Id = catalog3Id;
  }


  public String getIsEnabled() {
    return isEnabled;
  }

  public void setIsEnabled(String isEnabled) {
    this.isEnabled = isEnabled;
  }

}

package com.shn.gmall.bean;


import java.io.Serializable;

/**
 * @author sss
 */
public class BaseCatalog2 implements Serializable {

  private static final long serialVersionUID = -8041069605366733384L;
  private String id;
  private String name;
  private String catalog1Id;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getCatalog1Id() {
    return catalog1Id;
  }

  public void setCatalog1Id(String catalog1Id) {
    this.catalog1Id = catalog1Id;
  }

}

package com.shn.gmall.bean;


import java.io.Serializable;

/**
 * @author sss
 */
public class BaseSaleAttr implements Serializable {

  private static final long serialVersionUID = -2785694063496016522L;
  private String id;
  private String name;


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

}

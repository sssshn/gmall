package com.shn.gmall.bean;


import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @author sss
 */
public class BaseCatalog1 implements Serializable {

  private static final long serialVersionUID = -9144953093230426824L;
  private String id;
  private String name;

  public List<BaseCatalog2> getBaseCatalog2List() {
    return baseCatalog2List;
  }

  public void setBaseCatalog2List(List<BaseCatalog2> baseCatalog2List) {
    this.baseCatalog2List = baseCatalog2List;
  }

  @Transient
  private List<BaseCatalog2> baseCatalog2List;


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

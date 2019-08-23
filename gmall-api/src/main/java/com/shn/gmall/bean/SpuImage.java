package com.shn.gmall.bean;


import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author sss
 */
public class SpuImage implements Serializable {

  @Id
  private String id;
  private String spuId;
  private String imgName;
  private String imgUrl;


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

}

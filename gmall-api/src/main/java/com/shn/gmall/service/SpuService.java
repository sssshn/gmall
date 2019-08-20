package com.shn.gmall.service;

import com.shn.gmall.bean.BaseSaleAttr;
import com.shn.gmall.bean.SpuInfo;

import java.util.List;

public interface SpuService {

    List<SpuInfo> getSpuList(String catalog3Id);

    List<BaseSaleAttr> baseSaleAttrList();

    void saveSpu(SpuInfo spuInfo);
}

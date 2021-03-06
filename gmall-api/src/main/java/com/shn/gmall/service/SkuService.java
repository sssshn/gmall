package com.shn.gmall.service;

import com.shn.gmall.bean.SkuInfo;

import java.util.List;

public interface SkuService {

    SkuInfo getSkuInfoById(String skuId);

    List<SkuInfo> getSkuInfoByCatalog3Id(String catalog3Id);

    SkuInfo spuImageListById(String skuId);

    List<SkuInfo> getSkuList(String spuId);

    void saveSku(SkuInfo skuInfo);
}

package com.shn.gmall.service;

import com.shn.gmall.bean.SkuInfo;

import java.util.List;

public interface SkuService {

    List<SkuInfo> getSkuList(String spuId);

    void saveSku(SkuInfo skuInfo);
}

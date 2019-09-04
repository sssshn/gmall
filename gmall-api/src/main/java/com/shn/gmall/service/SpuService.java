package com.shn.gmall.service;

import com.shn.gmall.bean.BaseSaleAttr;
import com.shn.gmall.bean.SkuInfo;
import com.shn.gmall.bean.SpuImage;
import com.shn.gmall.bean.SpuInfo;
import com.shn.gmall.bean.SpuSaleAttr;

import java.util.List;
import java.util.Map;

/**
 * @author sss
 */
public interface SpuService {

    /**
     *
     * @param spuId
     * @return
     */
    List<SkuInfo> getSkuSaleAttrValueListBySpu(String spuId);

    List<SpuSaleAttr> spuSaleAttrListCheckBySku(Map<String, String> map);

    List<SpuInfo> getSpuList(String catalog3Id);

    List<BaseSaleAttr> baseSaleAttrList();

    void saveSpu(SpuInfo spuInfo);

    List<SpuSaleAttr> getSpuSaleAttr(String spuId);

    List<SpuImage> getSpuImg(String spuId);
}

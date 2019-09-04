package com.shn.gmall.manage.mapper;

import com.shn.gmall.bean.SkuInfo;
import com.shn.gmall.bean.SpuSaleAttr;
import com.shn.gmall.bean.SpuSaleAttrValue;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author sss
 */
public interface SpuSaleAttrValueMapper extends Mapper<SpuSaleAttrValue> {

    /**
     * 根据spuId和skuId查询当前spu所有的销售属性和当前sku所对应的销售属性
     * @param map
     * @return
     */
    List<SpuSaleAttr> selectSpuSaleAttrListCheckBySku(Map<String, String> map);

    /**
     * 根据spuId查询当前spu下sku的所有销售属性
     * @param spuId
     * @return
     */
    List<SkuInfo> selectSkuSaleAttrValueListBySpu(String spuId);
}

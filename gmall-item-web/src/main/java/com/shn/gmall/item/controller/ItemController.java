package com.shn.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.shn.gmall.bean.SkuInfo;
import com.shn.gmall.bean.SkuSaleAttrValue;
import com.shn.gmall.bean.SpuSaleAttr;
import com.shn.gmall.service.SkuService;
import com.shn.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sss
 */
@Controller
public class ItemController {

    @Reference
    private SkuService skuService;
    @Reference
    private SpuService spuService;

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable("skuId") String skuId , ModelMap modelMap) {
        SkuInfo skuInfo = skuService.spuImageListById(skuId);
        modelMap.addAttribute("skuInfo", skuInfo);

        if (skuInfo != null) {

            String spuId = skuInfo.getSpuId();
            Map<String, String> map = new HashMap<>(16);
            map.put("spuId", spuId);
            map.put("skuId", skuId);
            List<SpuSaleAttr> spuSaleAttrList = spuService.spuSaleAttrListCheckBySku(map);
            modelMap.addAttribute("spuSaleAttrListCheckBySku", spuSaleAttrList);

            List<SkuInfo> skuInfoList = spuService.getSkuSaleAttrValueListBySpu(spuId);
            Map<String, Integer> map1 = new HashMap(16);
            for (SkuInfo info : skuInfoList) {
                String key = "";
                Integer id = info.getId();
                for (SkuSaleAttrValue skuSaleAttrValue : info.getSkuSaleAttrValueList()) {
                    key = key + "!" + skuSaleAttrValue.getSaleAttrValueId();
                }
                map1.put(key, id);
            }
            String jsonString = JSON.toJSONString(map1);
            modelMap.addAttribute("skuJson", jsonString);
        }
        return "item";
    }
}

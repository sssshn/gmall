package com.shn.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shn.gmall.bean.SkuInfo;
import com.shn.gmall.service.SkuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sss
 */
@RestController
public class SkuController {

    @Reference
    private SkuService skuService;

    @RequestMapping("/saveSku")
    public String saveSku(SkuInfo skuInfo) {
        try {
            skuService.saveSku(skuInfo);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    @RequestMapping("/getSkuList")
    public List<SkuInfo> getSkuList(String spuId) {
        return skuService.getSkuList(spuId);
    }
}

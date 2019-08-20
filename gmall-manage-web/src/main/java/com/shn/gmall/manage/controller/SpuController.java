package com.shn.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shn.gmall.bean.BaseSaleAttr;
import com.shn.gmall.bean.SpuInfo;
import com.shn.gmall.service.SpuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpuController {

    @Reference
    private SpuService spuService;

    @RequestMapping("/getSpuList")
    public List<SpuInfo> reloadSpuList(String catalog3Id) {
        return spuService.getSpuList(catalog3Id);
    }

    @RequestMapping("/baseSaleAttrList")
    public List<BaseSaleAttr> baseSaleAttrList() {
        return spuService.baseSaleAttrList();
    }

    @RequestMapping("/saveSpu")
    public String saveSpu(SpuInfo spuInfo) {
        spuService.saveSpu(spuInfo);
        return "success";
    }
}

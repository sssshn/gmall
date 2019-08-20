package com.shn.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shn.gmall.bean.BaseCatalog1;
import com.shn.gmall.bean.BaseCatalog2;
import com.shn.gmall.bean.BaseCatalog3;
import com.shn.gmall.service.CatalogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CatalogControl {

    @Reference
    private CatalogService catalogService;

    @RequestMapping("/get_data1")
    public List<BaseCatalog1> getData1() {
        return catalogService.getData1();
    }

    @RequestMapping("/get_data2")
    public List<BaseCatalog2> getData2(String catalog1Id) {
        return catalogService.getData2(catalog1Id);
    }

    @RequestMapping("/get_data3")
    public List<BaseCatalog3> getData3(String catalog2Id) {
        return catalogService.getData3(catalog2Id);
    }
}

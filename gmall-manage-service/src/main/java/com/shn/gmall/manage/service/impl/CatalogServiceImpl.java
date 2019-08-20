package com.shn.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shn.gmall.bean.BaseCatalog1;
import com.shn.gmall.bean.BaseCatalog2;
import com.shn.gmall.bean.BaseCatalog3;
import com.shn.gmall.manage.mapper.BaseCatalog1Mapper;
import com.shn.gmall.manage.mapper.BaseCatalog2Mapper;
import com.shn.gmall.manage.mapper.BaseCatalog3Mapper;
import com.shn.gmall.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;
    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;
    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;

    @Override
    public List<BaseCatalog1> getData1() {
        return baseCatalog1Mapper.selectAll();
    }

    @Override
    public List<BaseCatalog2> getData2(String catalog1Id) {
        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);
        return baseCatalog2Mapper.select(baseCatalog2);
    }

    @Override
    public List<BaseCatalog3> getData3(String catalog2Id) {
        BaseCatalog3 baseCatalog3 = new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catalog2Id);
        return baseCatalog3Mapper.select(baseCatalog3);
    }
}

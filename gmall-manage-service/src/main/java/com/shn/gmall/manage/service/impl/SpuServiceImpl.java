package com.shn.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shn.gmall.bean.BaseSaleAttr;
import com.shn.gmall.bean.SpuInfo;
import com.shn.gmall.bean.SpuSaleAttr;
import com.shn.gmall.bean.SpuSaleAttrValue;
import com.shn.gmall.manage.mapper.BaseSaleAttrMapper;
import com.shn.gmall.manage.mapper.SpuMapper;
import com.shn.gmall.manage.mapper.SpuSaleAttrMapper;
import com.shn.gmall.manage.mapper.SpuSaleAttrValueMapper;
import com.shn.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author sss
 */
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;

    @Override
    public List<SpuInfo> getSpuList(String catalog3Id) {
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setCatalog3Id(catalog3Id);
        return spuMapper.select(spuInfo);
    }

    @Override
    public List<BaseSaleAttr> baseSaleAttrList() {
        return baseSaleAttrMapper.selectAll();
    }

    @Override
    public void saveSpu(SpuInfo spuInfo) {
        spuMapper.insertSelective(spuInfo);
        for (SpuSaleAttr spuSaleAttr : spuInfo.getSpuSaleAttrList()) {
            spuSaleAttr.setSpuId(spuInfo.getId());
            spuSaleAttrMapper.insert(spuSaleAttr);
            for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttr.getSpuSaleAttrValueList()) {
                spuSaleAttrValue.setSpuId(spuInfo.getId());
                spuSaleAttrValue.setSaleAttrId(spuSaleAttr.getSaleAttrId());
                spuSaleAttrValueMapper.insert(spuSaleAttrValue);
            }
        }
    }
}

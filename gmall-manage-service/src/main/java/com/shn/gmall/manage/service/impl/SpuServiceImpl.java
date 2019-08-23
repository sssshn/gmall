package com.shn.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shn.gmall.bean.BaseSaleAttr;
import com.shn.gmall.bean.SpuImage;
import com.shn.gmall.bean.SpuInfo;
import com.shn.gmall.bean.SpuSaleAttr;
import com.shn.gmall.bean.SpuSaleAttrValue;
import com.shn.gmall.manage.mapper.BaseSaleAttrMapper;
import com.shn.gmall.manage.mapper.SpuImageMapper;
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
    @Autowired
    private SpuImageMapper spuImageMapper;

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
        //销售属性和销售属性值保存
        for (SpuSaleAttr spuSaleAttr : spuInfo.getSpuSaleAttrList()) {
            spuSaleAttr.setSpuId(spuInfo.getId());
            spuSaleAttrMapper.insert(spuSaleAttr);
            for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttr.getSpuSaleAttrValueList()) {
                spuSaleAttrValue.setSpuId(spuInfo.getId());
                spuSaleAttrValue.setSaleAttrId(spuSaleAttr.getSaleAttrId());
                spuSaleAttrValueMapper.insert(spuSaleAttrValue);
            }
        }

        //spu图片上传保存
        for (SpuImage spuImage : spuInfo.getSpuImageList()) {
            spuImage.setSpuId(spuInfo.getId());
            spuImageMapper.insert(spuImage);
        }
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttr(String spuId) {
        SpuSaleAttr spuSaleAttr = new SpuSaleAttr();
        spuSaleAttr.setSpuId(spuId);
        List<SpuSaleAttr> spuSaleAttrList = spuSaleAttrMapper.select(spuSaleAttr);
        for (SpuSaleAttr saleAttr : spuSaleAttrList) {
            SpuSaleAttrValue spuSaleAttrValue = new SpuSaleAttrValue();
            spuSaleAttrValue.setSpuId(spuId);
            spuSaleAttrValue.setSaleAttrId(saleAttr.getSaleAttrId());
            List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttrValueMapper.select(spuSaleAttrValue);
            saleAttr.setSpuSaleAttrValueList(spuSaleAttrValueList);
        }
        return spuSaleAttrList;
    }

    @Override
    public List<SpuImage> getSpuImg(String spuId) {
        SpuImage spuImage = new SpuImage();
        spuImage.setSpuId(spuId);
        return spuImageMapper.select(spuImage);
    }
}

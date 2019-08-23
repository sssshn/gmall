package com.shn.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shn.gmall.bean.BaseAttrInfo;
import com.shn.gmall.bean.BaseAttrValue;
import com.shn.gmall.manage.mapper.AttrMapper;
import com.shn.gmall.manage.mapper.AttrValueMapper;
import com.shn.gmall.service.AttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author sss
 */
@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    private AttrMapper attrMapper;

    @Autowired
    private AttrValueMapper attrValueMapper;

    @Override
    public void deleteAttr(String id) {
        attrMapper.deleteByPrimaryKey(id);

        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(id);
        attrValueMapper.delete(baseAttrValue);
    }

    @Override
    public List<BaseAttrValue> updateAttr(String id) {
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(id);
        return attrValueMapper.select(baseAttrValue);
    }

    @Override
    public void saveAttr(BaseAttrInfo baseAttrInfo) {
        //修改
        if (StringUtils.isNotBlank(baseAttrInfo.getId())) {
            attrMapper.updateByPrimaryKeySelective(baseAttrInfo);

            BaseAttrValue baseAttrValue = new BaseAttrValue();
            baseAttrValue.setAttrId(baseAttrInfo.getId());
            attrValueMapper.delete(baseAttrValue);

            List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
            for (BaseAttrValue baseAttrValue1 : attrValueList) {
                baseAttrValue1.setAttrId(baseAttrInfo.getId());
                attrValueMapper.insert(baseAttrValue1);
            }

        } else {
            //新增
            attrMapper.insertSelective(baseAttrInfo);

            List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
            for (BaseAttrValue baseAttrValue : attrValueList) {
                baseAttrValue.setAttrId(baseAttrInfo.getId());
                attrValueMapper.insert(baseAttrValue);
            }
        }
    }

    @Override
    public List<BaseAttrInfo> getPlatformAttr(String catalog3Id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3Id);
        List<BaseAttrInfo> baseAttrInfoList = attrMapper.select(baseAttrInfo);
        for (BaseAttrInfo attrInfo : baseAttrInfoList) {
            BaseAttrValue baseAttrValue = new BaseAttrValue();
            baseAttrValue.setAttrId(attrInfo.getId());
            List<BaseAttrValue> baseAttrValueList = attrValueMapper.select(baseAttrValue);
            attrInfo.setAttrValueList(baseAttrValueList);
        }
        return baseAttrInfoList;
    }

    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3Id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3Id);
        return attrMapper.select(baseAttrInfo);
    }
}

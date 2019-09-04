package com.shn.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.shn.gmall.bean.SkuAttrValue;
import com.shn.gmall.bean.SkuImage;
import com.shn.gmall.bean.SkuInfo;
import com.shn.gmall.bean.SkuSaleAttrValue;
import com.shn.gmall.manage.mapper.SkuAttrValueMapper;
import com.shn.gmall.manage.mapper.SkuImageMapper;
import com.shn.gmall.manage.mapper.SkuInfoMapper;
import com.shn.gmall.manage.mapper.SkuSaleAttrValueMapper;
import com.shn.gmall.service.SkuService;
import com.shn.gmall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuInfoMapper skuInfoMapper;
    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;
    @Autowired
    private SkuImageMapper skuImageMapper;
    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<SkuInfo> getSkuInfoByCatalog3Id(String catalog3Id) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setCatalog3Id(catalog3Id);
        List<SkuInfo> skuInfoList = skuInfoMapper.select(skuInfo);
        SkuAttrValue skuAttrValue = new SkuAttrValue();
        for (SkuInfo info : skuInfoList) {
            skuAttrValue.setSkuId(info.getId());
            List<SkuAttrValue> skuAttrValueList = skuAttrValueMapper.select(skuAttrValue);
            info.setSkuAttrValueList(skuAttrValueList);
        }
        return skuInfoList;
    }

    @Override
    public SkuInfo spuImageListById(String skuId) {

        Jedis jedis = redisUtil.getJedis();
        SkuInfo skuInfo = null;

        //查询redis缓存
        String key = "sku:" + skuId + ":info";
        String val = jedis.get(key);

        if ("empty".equals(val)) {
            return skuInfo;
        }

        if (StringUtils.isBlank(val)) {
            //申请缓存锁
            String ok = jedis.set("sku:" + skuId + ":lock", "value", "nx", "px", 3000);
            if ("OK".equals(ok)) {
                skuInfo = getSkuByIdFormDb(skuId);

                if (skuInfo != null) {
                    //同步缓存
                    jedis.set(key, JSON.toJSONString(skuInfo));
                } else {
                    //通知同伴
                    jedis.setex("sku:" + skuId + ":info", 10, "empty");
                }
                //归还缓存锁
                jedis.del("sku:" + skuId + ":lock");
            } else {
                //没有拿到缓存锁，自旋
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                spuImageListById(skuId);
            }

            return skuInfo;
        }

        SkuInfo skuInfo2 = JSON.parseObject(val, SkuInfo.class);
        return skuInfo2;
    }

    private SkuInfo getSkuByIdFormDb(String skuId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(Integer.parseInt(skuId));
        SkuInfo skuInfo1 = skuInfoMapper.selectOne(skuInfo);
        if (skuInfo1 != null) {
            SkuImage skuImage = new SkuImage();
            skuImage.setSkuId(Integer.parseInt(skuId));
            List<SkuImage> select = skuImageMapper.select(skuImage);
            skuInfo1.setSkuImageList(select);
            }
        return skuInfo1;
    }

    @Override
    public List<SkuInfo> getSkuList(String spuId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setSpuId(spuId);
        List<SkuInfo> select = skuInfoMapper.select(skuInfo);
        return select;
    }

    @Override
    public void saveSku(SkuInfo skuInfo) {
        skuInfoMapper.insertSelective(skuInfo);

        for (SkuAttrValue skuAttrValue : skuInfo.getSkuAttrValueList()) {
            skuAttrValue.setSkuId(skuInfo.getId());
            skuAttrValueMapper.insertSelective(skuAttrValue);
        }

        for (SkuImage skuImage : skuInfo.getSkuImageList()) {
            skuImage.setSkuId(skuInfo.getId());
            skuImageMapper.insertSelective(skuImage);
        }

        for (SkuSaleAttrValue skuSaleAttrValue : skuInfo.getSkuSaleAttrValueList()) {
            skuSaleAttrValue.setSkuId(skuInfo.getId());
            skuSaleAttrValueMapper.insertSelective(skuSaleAttrValue);
        }
    }
}

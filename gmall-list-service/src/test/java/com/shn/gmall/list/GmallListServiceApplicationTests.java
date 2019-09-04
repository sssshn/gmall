package com.shn.gmall.list;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shn.gmall.bean.SkuInfo;
import com.shn.gmall.bean.SkuLsInfo;
import com.shn.gmall.service.SkuService;
import io.searchbox.client.JestClient;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallListServiceApplicationTests {

    @Autowired
    private JestClient jestClient;
    @Reference
    private SkuService skuService;

    @Test
    public void contextLoads() {
        //根据三级分类id获取sku
        List<SkuInfo> skuInfoList = skuService.getSkuInfoByCatalog3Id("4260");
        //需要导入es的数据
        List<SkuLsInfo> skuLsInfoList = new ArrayList<>();
        SkuLsInfo skuLsInfo = new SkuLsInfo();
        for (SkuInfo skuInfo : skuInfoList) {
            try {
                BeanUtils.copyProperties(skuLsInfo, skuInfo);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            skuLsInfoList.add(skuLsInfo);
        }

        System.out.println("导入es中");
    }

}

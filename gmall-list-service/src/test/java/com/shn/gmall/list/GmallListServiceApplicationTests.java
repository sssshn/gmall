package com.shn.gmall.list;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shn.gmall.bean.SkuInfo;
import com.shn.gmall.bean.SkuLsInfo;
import com.shn.gmall.service.SkuService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
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

    }

    public void importEs() {
        //根据三级分类id获取sku
        List<SkuInfo> skuInfoList = skuService.getSkuInfoByCatalog3Id("4260");
        //需要导入es的数据
        List<SkuLsInfo> skuLsInfoList = new ArrayList<>();
        for (SkuInfo skuInfo : skuInfoList) {
            SkuLsInfo skuLsInfo = new SkuLsInfo();
            try {
                BeanUtils.copyProperties(skuLsInfo, skuInfo);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            skuLsInfoList.add(skuLsInfo);
        }

        //导入到es中
        for (SkuLsInfo skuLsInfo : skuLsInfoList) {
            String id = String.valueOf(skuLsInfo.getId());
            Index build = new Index.Builder(skuLsInfo).index("gmall").type("SkuLsInfo").id(id).build();
            System.out.println(build.toString());

            try {
                jestClient.execute(build);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

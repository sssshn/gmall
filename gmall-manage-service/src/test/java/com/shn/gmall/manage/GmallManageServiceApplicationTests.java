package com.shn.gmall.manage;

import com.alibaba.fastjson.JSON;
import com.shn.gmall.bean.BaseCatalog1;
import com.shn.gmall.manage.mapper.BaseCatalog1Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageServiceApplicationTests {

    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;

    @Test
    public void contextLoads() {
        List<BaseCatalog1> baseCatalog1s = baseCatalog1Mapper.selectAllCatalogId();
        String s = JSON.toJSONString(baseCatalog1s);
        File file = new File("D://text.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(s.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }

}

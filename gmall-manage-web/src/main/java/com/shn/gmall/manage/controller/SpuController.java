package com.shn.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shn.gmall.bean.BaseSaleAttr;
import com.shn.gmall.bean.SpuImage;
import com.shn.gmall.bean.SpuInfo;
import com.shn.gmall.bean.SpuSaleAttr;
import com.shn.gmall.manage.util.MyUploadUtil;
import com.shn.gmall.service.SpuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        String imgUrl = MyUploadUtil.uploadImg(file);
        return imgUrl;
    }

    @RequestMapping("/getSpuSaleAttr")
    public List<SpuSaleAttr> getSpuSaleAttr(String spuId) {
        return spuService.getSpuSaleAttr(spuId);
    }

    @RequestMapping("/getSpuImg")
    public List<SpuImage> getSpuImg(String spuId) {
        return spuService.getSpuImg(spuId);
    }
}

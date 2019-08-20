package com.shn.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shn.gmall.bean.BaseAttrInfo;
import com.shn.gmall.bean.BaseAttrValue;
import com.shn.gmall.service.AttrService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sss
 */
@RestController
public class AttrControl {

    @Reference
    private AttrService attrService;

    /**
     * 根据三级id查属性
     * @param catalog3Id
     * @return
     */
    @RequestMapping("/getAttrList")
    public List<BaseAttrInfo> getAttrList(String catalog3Id) {
        return attrService.getAttrList(catalog3Id);
    }

    /**
     * 新增或修改
     * @param baseAttrInfo
     * @return
     */
    @RequestMapping("/saveAttr")
    public String saveAttr(BaseAttrInfo baseAttrInfo) {
        if (baseAttrInfo == null) {
            return "failure";
        }
        attrService.saveAttr(baseAttrInfo);
        return "success";
    }

    /**
     * 修改数据回显
     * @param id
     * @return
     */
    @RequestMapping("/updateAttr")
    public List<BaseAttrValue> updateAttr(String id) {
       return attrService.updateAttr(id);
    }

    @RequestMapping("/deleteAttr")
    public String deleteAttr(String id) {
        try {
            attrService.deleteAttr(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }
}

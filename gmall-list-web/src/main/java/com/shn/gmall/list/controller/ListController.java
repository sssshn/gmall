package com.shn.gmall.list.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shn.gmall.bean.BaseAttrInfo;
import com.shn.gmall.bean.BaseAttrValue;
import com.shn.gmall.bean.Crumb;
import com.shn.gmall.bean.SkuLsAttrValue;
import com.shn.gmall.bean.SkuLsInfo;
import com.shn.gmall.bean.SkuLsParam;
import com.shn.gmall.service.AttrService;
import com.shn.gmall.service.ListService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author sss
 */
@Controller
public class ListController {

    @Reference
    private ListService listService;
    @Reference
    private AttrService attrService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/list.html")
    public String list(SkuLsParam skuLsParam, ModelMap modelMap) {

        //请求参数中的所有属性值
        String[] paramValueIds = skuLsParam.getValueId();


        List<SkuLsInfo> skuLsInfoList = listService.getSkuLsInfoByParam(skuLsParam);
        Set<Integer> valueIdSet = new HashSet<>();
        for (SkuLsInfo skuLsInfo : skuLsInfoList) {
            if (skuLsInfo.getSkuAttrValueList() != null) {
                for (SkuLsAttrValue skuLsAttrValue : skuLsInfo.getSkuAttrValueList()) {
                    valueIdSet.add(skuLsAttrValue.getValueId());
                }
            }
        }

        //获取当前的url参数
        String urlParam = getUrl(skuLsParam, "");

        List<Crumb> crumbList = new ArrayList<>();

        //查询sku所对应的所有平台属性信息并把已经筛选的属性值删除
        List<BaseAttrInfo> attrList = attrService.getAttrInfoByValueId(valueIdSet);
        if (paramValueIds != null && paramValueIds.length > 0) {
            Iterator<BaseAttrInfo> iterator = attrList.iterator();
            while (iterator.hasNext()) {
                List<BaseAttrValue> attrValueList = iterator.next().getAttrValueList();
                for (String s : paramValueIds) {
                    if (attrValueList != null && attrValueList.size() > 0) {
                        for (BaseAttrValue baseAttrValue : attrValueList) {
                            if (baseAttrValue.getId().equals(s)) {
                                //面包屑功能
                                Crumb crumb = new Crumb();
                                String crumbUrl = getUrl(skuLsParam, s);
                                crumb.setValueName(baseAttrValue.getValueName());
                                crumb.setUrlParam(crumbUrl);
                                crumbList.add(crumb);
                                iterator.remove();
                            }
                        }
                    }
                }
            }
        }

        modelMap.addAttribute("skuLsInfoList", skuLsInfoList);
        modelMap.addAttribute("attrList", attrList);
        modelMap.addAttribute("urlParam", urlParam);
        modelMap.addAttribute("attrValueSelectedList", crumbList);
        return "list";
    }

    public String getUrl(SkuLsParam skuLsParam, String id) {
        String[] paramValueIds = skuLsParam.getValueId();
        StringBuilder urlParam = new StringBuilder();
        if (skuLsParam != null) {
            if (StringUtils.isNotBlank(skuLsParam.getCatalog3Id())) {
                if (urlParam.length() == 0) {
                    urlParam.append("catalog3Id=" + skuLsParam.getCatalog3Id());
                } else {
                    urlParam.append("&catalog3Id=" + skuLsParam.getCatalog3Id());
                }

            }
            //id为面包屑的id
            if (paramValueIds != null && paramValueIds.length > 0) {
                for (String s : paramValueIds) {
                    if (!s.equals(id)) {
                        urlParam.append("&valueId=" + s);
                    }

                }
            }

            if (StringUtils.isNotBlank(skuLsParam.getKeyword())) {
                if (urlParam.length() == 0) {
                    urlParam.append("&keyword=" + skuLsParam.getKeyword());
                } else {
                    urlParam.append("keyword=" + skuLsParam.getKeyword());
                }
            }
        }
        return urlParam.toString();
    }
}

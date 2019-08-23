package com.shn.gmall.service;

import com.shn.gmall.bean.BaseAttrInfo;
import com.shn.gmall.bean.BaseAttrValue;

import java.util.List;

/**
 * @author sss
 */
public interface AttrService {

    List<BaseAttrInfo> getPlatformAttr(String catalog3Id);

    /**
     * 根据三级ID查询属性
     * @param catalog3Id
     * @return
     */
    List<BaseAttrInfo> getAttrList(String catalog3Id);

    /**
     * 保存属性名称和属性值
     * @param baseAttrInfo
     */
    void saveAttr(BaseAttrInfo baseAttrInfo);

    /**
     * 回显属性值
     * @param id
     * @return
     */
    List<BaseAttrValue> updateAttr(String id);

    /**
     * 删除属性及属性值
     * @param id
     * @return
     */
    void deleteAttr(String id);

}

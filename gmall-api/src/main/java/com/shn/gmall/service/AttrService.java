package com.shn.gmall.service;

import com.shn.gmall.bean.BaseAttrInfo;
import com.shn.gmall.bean.BaseAttrValue;

import java.util.List;
import java.util.Set;

/**
 * @author sss
 */
public interface AttrService {

    /**
     *
     * @param valueIdSet
     * @return
     */
    List<BaseAttrInfo> getAttrInfoByValueId(Set<Integer> valueIdSet);

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

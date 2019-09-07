package com.shn.gmall.manage.mapper;

import com.shn.gmall.bean.BaseAttrInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author sss
 */
public interface AttrMapper extends Mapper<BaseAttrInfo> {

    List<BaseAttrInfo> getInfoByValueId(@Param("valueId") String valueId);
}

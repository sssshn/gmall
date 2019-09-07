package com.shn.gmall.manage.mapper;

import com.shn.gmall.bean.BaseCatalog1;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author sss
 */
public interface BaseCatalog1Mapper extends Mapper<BaseCatalog1> {

    List<BaseCatalog1> selectAllCatalogId();
}

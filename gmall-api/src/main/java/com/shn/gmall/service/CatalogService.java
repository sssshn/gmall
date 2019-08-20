package com.shn.gmall.service;

import com.shn.gmall.bean.BaseCatalog1;
import com.shn.gmall.bean.BaseCatalog2;
import com.shn.gmall.bean.BaseCatalog3;

import java.util.List;

public interface CatalogService {

    /**
     *
     * @return
     */
    List<BaseCatalog1> getData1();

    /**
     *
     * @param catalog1Id
     * @return
     */
    List<BaseCatalog2> getData2(String catalog1Id);

    /**
     *
     * @param catalog2Id
     * @return
     */
    List<BaseCatalog3> getData3(String catalog2Id);
}

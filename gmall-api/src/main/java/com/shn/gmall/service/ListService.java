package com.shn.gmall.service;

import com.shn.gmall.bean.SkuLsInfo;
import com.shn.gmall.bean.SkuLsParam;

import java.util.List;

public interface ListService {

    List<SkuLsInfo> getSkuLsInfoByParam(SkuLsParam skuLsParam);
}

package com.shn.gmall.list.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shn.gmall.bean.SkuLsInfo;
import com.shn.gmall.bean.SkuLsParam;
import com.shn.gmall.service.ListService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListServiceImpl implements ListService {

    @Autowired
    private JestClient jestClient;

    @Override
    public List<SkuLsInfo> getSkuLsInfoByParam(SkuLsParam skuLsParam) {
        List<SkuLsInfo> skuLsInfoList = new ArrayList<>();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (skuLsParam != null && StringUtils.isNotBlank(skuLsParam.getCatalog3Id())) {
            TermQueryBuilder termQueryBuilder = new TermQueryBuilder("catalog3Id", skuLsParam.getCatalog3Id());
            boolQueryBuilder.filter(termQueryBuilder);
        }

        if (skuLsParam != null && skuLsParam.getValueId() != null && skuLsParam.getValueId().length > 0) {
            for (String id : skuLsParam.getValueId()) {
                TermQueryBuilder termQueryBuilder1 = new TermQueryBuilder("skuAttrValueList.valueId", id);
                boolQueryBuilder.filter(termQueryBuilder1);
            }
        }

        if (skuLsParam != null && StringUtils.isNotBlank(skuLsParam.getKeyword())) {
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("skuName", skuLsParam.getKeyword());
            boolQueryBuilder.must(matchQueryBuilder);
        }


        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.size(100);
        searchSourceBuilder.from(0);
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex("gmall").addType("SkuLsInfo").build();
        try {
            SearchResult searchResult = jestClient.execute(search);
            List<SearchResult.Hit<SkuLsInfo, Void>> hits = searchResult.getHits(SkuLsInfo.class);
            for (SearchResult.Hit<SkuLsInfo, Void> hit : hits) {
                SkuLsInfo skuLsInfo = hit.source;
                skuLsInfoList.add(skuLsInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skuLsInfoList;
    }
}

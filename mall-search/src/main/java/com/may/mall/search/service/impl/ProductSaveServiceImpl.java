package com.may.mall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.may.common.to.es.SkuEsModle;
import com.may.mall.search.config.MallElasticSearchConfig;
import com.may.mall.search.constant.EsConstant;
import com.may.mall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductSaveServiceImpl implements ProductSaveService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public boolean productStatusUp(List<SkuEsModle> skuEsModles) throws IOException {
        //保存到 es
        //1、给es中建立索引，product
        //2、保存数据
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModle modle:skuEsModles){
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(modle.getSkuId().toString());
            String s = JSON.toJSONString(modle);
            indexRequest.source(s, XContentType.JSON);
            bulkRequest.add(indexRequest);

        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, MallElasticSearchConfig.COMMON_OPTIONS);
        boolean b = bulk.hasFailures();
        List<String> collect = Arrays.stream(bulk.getItems()).map(item -> {
            return item.getId();

        }).collect(Collectors.toList());

        log.error("商品上架错误：{}",collect);
        return b;
    }
}

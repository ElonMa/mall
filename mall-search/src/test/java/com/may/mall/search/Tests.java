package com.may.mall.search;

import com.alibaba.fastjson.JSON;
import com.may.mall.search.config.MallElasticSearchConfig;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.directory.SearchResult;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {

    @Autowired
    private RestHighLevelClient client;


    /**
     * 测试存储数据
     */
    @Test
    public void  searchData() throws IOException {
        //创建索引请求
        SearchRequest searchRequest = new SearchRequest();
        //指定索引
        searchRequest.indices("bank");
        //指定DSL检索条件
        SearchSourceBuilder searchSourceBuilder  = new SearchSourceBuilder();
        // 构造检索条件
        searchSourceBuilder.query(QueryBuilders.matchQuery("address","mill"));
        System.out.println(searchSourceBuilder.toString());
//        searchSourceBuilder.from();
//        searchSourceBuilder.size();
//        searchSourceBuilder.aggregation();


        searchRequest.source(searchSourceBuilder);
        //执行检索
        SearchResponse searchResponse = client.search(searchRequest,MallElasticSearchConfig.COMMON_OPTIONS);

        System.out.println(searchResponse.toString());
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit :searchHits){
            hit.getSourceAsString();
            System.out.println(hit.getSourceAsString());
        }

    }




    /**
     * 测试存储数据
     */
    @Test
    public void  indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
//        indexRequest.source("userName","zhangsan","age","18");
        User user = new User();
        user.setAge(100);
        user.setUserName("mayongfeng");
        String json = JSON.toJSONString(user);
        indexRequest.source(json, XContentType.JSON);
        //执行
        client.index(indexRequest, MallElasticSearchConfig.COMMON_OPTIONS);

    }

    @Data
    class User{
        String userName;
        Integer age;

    }


    @Test
    public void  contextLoads(){
        System.out.println(client);

    }
}

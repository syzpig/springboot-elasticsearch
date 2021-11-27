package com.syz.es.springboot.service;

import com.syz.es.springboot.models.Book;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class EsService {

    private static final String DEFAULT_ES_INDEX = "234";
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public Object saveIndex(Book book){
       return elasticsearchRestTemplate.createIndex(Book.class);
    }

    public Object saveIndexStr(String indexName){
        return elasticsearchRestTemplate.createIndex(indexName);
    }
    public Object getByName(String indexName){
        return elasticsearchRestTemplate.getSetting(indexName);
    }
    public Object query(String name) throws IOException {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("createTime");
        rangeQuery.gte(new Date());//开始时间
        rangeQuery.lte(new Date());//结束时间
        queryBuilder.filter(rangeQuery);
        queryBuilder.filter(QueryBuilders.termQuery("dealStatus", "1")); //状态查询

        queryBuilder.filter(QueryBuilders.termsQuery("ownerUserId", "用户Id"));
        //根据时间分组统计总数
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(queryBuilder);

        DateHistogramAggregationBuilder fieldBuilder = AggregationBuilders.dateHistogram("groupCreateDate").field("createTime");
        ValueCountAggregationBuilder customerNumBuilder = AggregationBuilders.count("countCustomerNum").field("customerId");
        sourceBuilder.aggregation(fieldBuilder.subAggregation(customerNumBuilder));
        //分组查询拼接求各组数
        SearchRequest searchRequest = new SearchRequest(DEFAULT_ES_INDEX);
        searchRequest.types("_doc");
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse= elasticsearchRestTemplate.getClient().search(searchRequest, RequestOptions.DEFAULT);
        return searchResponse;
    }

}

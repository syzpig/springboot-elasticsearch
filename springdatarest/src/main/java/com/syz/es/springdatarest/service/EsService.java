package com.syz.es.springdatarest.service;

import com.syz.es.springdatarest.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.MoreLikeThisQuery;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

@Service
public class EsService {


    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public Object saveIndex(Book book){
       return elasticsearchRestTemplate.save(book);
    }
    public Object getById(String id){
/*        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()
                .and(new Criteria("clusterName").is("app"))
                .and(new Criteria("ip").is("127.0.0.1"))
                .and(new Criteria("appType").is("download"))
                .and(new Criteria("appName").is("appdownload"))
                .and(new Criteria("fileName").is("appdownload.log"))
                .and(new Criteria("logLeval").is("info"))
                .and(new Criteria("produceDateTime").greaterThanEqual(
                        startDate.getTime()).lessThanEqual(endDate.getTime()))
                .and(new Criteria("message").contains("haha"))).setPageable(
                new PageRequest(0, 10)).addSort(
                new Sort(new Sort.Order(Sort.Direction.DESC, "segEndlineNo")));
        Page<LogEntity> pages = elasticsearchTemplate.queryForPage(criteriaQuery,
                LogEntity.class);*/
        return elasticsearchRestTemplate.get(id,Book.class);
    }
    public Object getByName(String name){
        return elasticsearchRestTemplate.search(new MoreLikeThisQuery(),Book.class);
    }

}

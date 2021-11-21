package com.syz.es.springboot.service;

import com.syz.es.springboot.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.MoreLikeThisQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class EsService {


    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public Object saveIndex(Book book){
       return elasticsearchRestTemplate.save(book);
    }
    public Object getById(String id){
        return elasticsearchRestTemplate.get(id,Book.class);
    }
    public Object getByName(String name){
        Criteria criteria=new Criteria();
        criteria.endsWith("syz");
        CriteriaQuery query=new CriteriaQuery(criteria);
        return elasticsearchRestTemplate.search(query,Book.class);
    }

}

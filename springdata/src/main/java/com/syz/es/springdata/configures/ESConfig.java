package com.syz.es.springdata.configures;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 *  @author: syz
 *  @Date: 2021/11/8 12:22
 *  @Description:
 */ 

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.syz.es.springdata")
public interface ESConfig {
}

package com.dtdream.elasticsearch.dao;

import com.dtdream.elasticsearch.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleSearchRepository extends ElasticsearchRepository<Article,Long> {
}

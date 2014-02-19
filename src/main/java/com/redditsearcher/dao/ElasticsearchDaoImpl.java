package com.redditsearcher.dao;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.simpleQueryString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import com.redditsearcher.model.Link;

@Repository
public class ElasticsearchDaoImpl implements ElasticsearchDao {
    @Autowired
    ElasticsearchConverter elasticsearchConverter;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<Link> search(String query) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(simpleQueryString(query))
            .build();

        Page<ElasticLink> elasticLinks = elasticsearchTemplate.queryForPage(searchQuery, ElasticLink.class);
        List<Link> links = elasticsearchConverter.convertList(elasticLinks.getContent());

        return links;
    }
}

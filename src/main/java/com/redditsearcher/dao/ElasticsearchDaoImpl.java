package com.redditsearcher.dao;

import static org.elasticsearch.index.query.QueryBuilders.simpleQueryString;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

//        String script = "(0.08 / ((3.16*pow(10,-11)) * abs(currenttimeinmillis - doc['date'].date.getMillis()) + 0.05)) + 1.0";
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("currenttimeinmillis", (Long) new Date().getTime());
//        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders.matchAllQuery())
//                .add(ScoreFunctionBuilders.scriptFunction(script, params));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(simpleQueryString(query))
            .withPageable(new PageRequest(0, 100))
            .build();

        Page<ElasticLink> elasticLinks = elasticsearchTemplate.queryForPage(searchQuery, ElasticLink.class);
        List<Link> links = elasticsearchConverter.convertList(elasticLinks.getContent());

        return links;
    }
}

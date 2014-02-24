package com.redditsearcher.dao;

import static org.elasticsearch.index.query.QueryBuilders.functionScoreQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.elasticsearch.index.query.MatchQueryBuilder.Operator;
import org.elasticsearch.index.query.MatchQueryBuilder.Type;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
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

    private final transient Logger log = Logger.getLogger(this.getClass());

    @Override
    public List<Link> search(String query) {
        String scriptRecency = "(0.08 / ((3.16*pow(10,-11)) * abs(currenttimeinmillis - doc['created'].date.getMillis()) + 0.05)) + 1.0";
        String scriptRating = "doc['rating'].value";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("currenttimeinmillis", (Long) new Date().getTime());

        FunctionScoreQueryBuilder functionScoreQueryBuilder = functionScoreQuery(matchQuery("text", query)
                .type(Type.BOOLEAN)
                .operator(Operator.AND))
                .add(ScoreFunctionBuilders.scriptFunction(scriptRecency, params))
                .add(ScoreFunctionBuilders.scriptFunction(scriptRating));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(functionScoreQueryBuilder)
                .withPageable(new PageRequest(0, 100))
                .build();

        Page<ElasticLink> elasticLinks = elasticsearchTemplate.queryForPage(searchQuery, ElasticLink.class);
        List<Link> links = elasticsearchConverter.convertList(elasticLinks.getContent());

        if (log.isDebugEnabled()) {
            debug(elasticLinks.getContent());
        }

        return links;
    }

    private void debug(List<ElasticLink> links) {
        log.debug("#### Got " + links.size() + " results: ");
        for (ElasticLink link : links) {
            log.debug(link.toString());
        }
        log.debug("#### End ");
    }
}

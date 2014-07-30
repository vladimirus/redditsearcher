package com.redditsearcher.dao;

import static org.elasticsearch.index.query.QueryBuilders.functionScoreQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;

import com.redditsearcher.model.Link;
import org.elasticsearch.index.query.MatchQueryBuilder.Operator;
import org.elasticsearch.index.query.MatchQueryBuilder.Type;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ElasticsearchDaoImpl implements SearchDao {
    @Autowired
    ElasticsearchConverter elasticsearchConverter;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

//    private final transient Logger log = Logger.getLogger(this.getClass());

    @Override
    public List<Link> search(String query) {
        String scriptRecency = "(0.08 / ((3.16*pow(10,-11)) * abs(currentTimeInMillis - doc['created'].date.getMillis()) + 0.05)) + 1.0";
        String scriptRating = "doc['rating'].value";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("currentTimeInMillis", (Long) new Date().getTime());

        FunctionScoreQueryBuilder functionScoreQueryBuilder = functionScoreQuery(matchPhraseQuery("text", query)
                .type(Type.BOOLEAN)
                .operator(Operator.AND))
                .add(ScoreFunctionBuilders.scriptFunction(scriptRecency, params))
                .add(ScoreFunctionBuilders.scriptFunction(scriptRating));

        SortBuilder sortBuilder = SortBuilders.scoreSort();

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(functionScoreQueryBuilder)
                .withPageable(new PageRequest(0, 100))
                .withSort(sortBuilder)
                .build();

        Page<ElasticLink> elasticLinks = elasticsearchTemplate.queryForPage(searchQuery, ElasticLink.class);
        List<Link> links = elasticsearchConverter.convertList(elasticLinks.getContent());

        return links;
    }
}

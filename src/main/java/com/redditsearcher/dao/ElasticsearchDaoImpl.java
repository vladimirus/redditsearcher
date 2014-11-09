package com.redditsearcher.dao;

import static com.google.common.collect.Maps.newHashMap;
import static org.elasticsearch.common.unit.Fuzziness.AUTO;
import static org.elasticsearch.index.query.MatchQueryBuilder.Operator.AND;
import static org.elasticsearch.index.query.MatchQueryBuilder.Type.BOOLEAN;
import static org.elasticsearch.index.query.QueryBuilders.functionScoreQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders.scriptFunction;

import com.redditsearcher.model.Link;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Repository
public class ElasticsearchDaoImpl implements SearchDao {
    @Autowired
    ElasticsearchConverter elasticsearchConverter;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

//    private final transient Logger log = Logger.getLogger(this.getClass());

    @Override
    public Collection<Link> search(String query) {
        String scriptRecency = "(0.08 / ((3.16*pow(10,-11)) * abs(currentTimeInMillis - doc['created'].date.getMillis()) + 0.05)) + 1.0";
        String scriptRating = "doc['rating'].value";

        Map<String, Object> params = newHashMap();
        params.put("currentTimeInMillis", new Date().getTime());

        FunctionScoreQueryBuilder functionScoreQueryBuilder =
                functionScoreQuery(
                matchQuery("text", query)
                    .type(BOOLEAN)
                    .operator(AND)
                    .fuzziness(AUTO))
                .add(scriptFunction(scriptRecency, params))
                .add(scriptFunction(scriptRating));

        SortBuilder sortBuilder = SortBuilders.scoreSort();

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(functionScoreQueryBuilder)
                .withPageable(new PageRequest(0, 100))
                .withSort(sortBuilder)
                .build();

        Page<ElasticLink> elasticLinks = elasticsearchTemplate.queryForPage(searchQuery, ElasticLink.class);
        return elasticsearchConverter.convertList(elasticLinks.getContent());
    }

    @Override
    public long countAll() {
        return elasticsearchTemplate.count(new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build());
    }

    /**
     * This is only for integration test.
     *
     * @param elasticLink - link to save
     */
    public void save(ElasticLink elasticLink) {
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId(elasticLink.getId());
        indexQuery.setObject(elasticLink);
        elasticsearchTemplate.index(indexQuery);
    }

    /**
     * This is for integration tests.
     *
     * @param id - to delete.
     */
    public void delete(String id) {
        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setQuery(matchQuery("id", id));
        deleteQuery.setIndex("link");
        deleteQuery.setType("elasticlink");
        elasticsearchTemplate.delete(deleteQuery);
    }

    /**
     * This is for integration tests.
     */
    public void refresh() {
        elasticsearchTemplate.refresh("link", true);
    }
}

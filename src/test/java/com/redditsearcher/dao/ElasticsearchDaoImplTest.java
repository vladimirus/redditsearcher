package com.redditsearcher.dao;

import static com.redditsearcher.model.DomainFactory.aListOfElasticLink;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

@RunWith(MockitoJUnitRunner.class)
public class ElasticsearchDaoImplTest {
    private ElasticsearchDaoImpl dao;
    @Mock
    private ElasticsearchConverter elasticsearchConverter;
    @Mock
    private ElasticsearchTemplate elasticsearchTemplate;
    @Mock
    private FacetedPage<ElasticLink> pageOfElasticLinks;

    @Before
    public void before() {
        this.dao = new ElasticsearchDaoImpl();
        this.dao.elasticsearchConverter = elasticsearchConverter;
        this.dao.elasticsearchTemplate = elasticsearchTemplate;
    }

    @SuppressWarnings("unchecked")
    @Test
    public void search() {
        // given
        List<ElasticLink> elasticLinks = aListOfElasticLink(10);
        given(elasticsearchTemplate.queryForPage(isA(SearchQuery.class), any(Class.class))).willReturn(pageOfElasticLinks);
        given(pageOfElasticLinks.getContent()).willReturn(elasticLinks);

        // when
        dao.search("test");

        // then
        verify(elasticsearchTemplate).queryForPage(isA(SearchQuery.class), any(Class.class));
        verify(elasticsearchConverter).convertList(elasticLinks);
    }
}

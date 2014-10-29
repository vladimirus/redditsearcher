package com.redditsearcher.integration;

import static com.redditsearcher.model.DomainFactory.anElasticLink;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.redditsearcher.dao.ElasticLink;
import com.redditsearcher.dao.ElasticsearchDaoImpl;
import com.redditsearcher.model.Link;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

/**
 * Integration test for elasticsearch instance.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appCtx/*.xml")
public class ElasticSearchIT {

    @Autowired
    private ElasticsearchDaoImpl elasticsearchDao;

    private ElasticLink elasticLink;

    @Before
    public void before() {
        elasticLink = anElasticLink();
    }

    @After
    public void after() {
        elasticsearchDao.delete(elasticLink.getId());
    }

    @Test
    public void shouldFind() {
        // given
        elasticLink.setText("this is some text it vovatest");
        elasticsearchDao.save(elasticLink);

        // when
        elasticsearchDao.refresh();
        Collection<Link> links = elasticsearchDao.search("aa thi tex som vovatest");

        // then
        assertThat(links, hasSize(1));
    }

    @Test
    public void shouldNotFind() {
        // given
        elasticLink.setText("this is some text vovatest");
        elasticsearchDao.save(elasticLink);

        // when
        elasticsearchDao.refresh();
        Collection<Link> links = elasticsearchDao.search("aa thi tes som vovatext");

        // then
        assertThat(links, is(empty()));
    }
}

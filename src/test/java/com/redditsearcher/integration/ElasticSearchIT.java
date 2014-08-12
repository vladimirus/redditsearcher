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

import java.util.List;

/**
 * Integration test for elasticsearch instance.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appCtx/*.xml")
public class ElasticSearchIT {

    @Autowired
    private ElasticsearchDaoImpl searchDao;

    private ElasticLink elasticLink;

    @Before
    public void before() {
        elasticLink = anElasticLink();
    }

    @After
    public void after() {
        searchDao.delete(elasticLink.getId());
    }

    @Test
    public void dummy() {       //so integration phase doesn't fail without any tests
        assertThat(true, is(true));
    }

    @Test
    public void shouldFind() {
        // given
        elasticLink.setText("this is some text it vovatest");
        searchDao.save(elasticLink);

        // when
        searchDao.refresh();
        List<Link> links = searchDao.search("aa thi tex som vovatest");

        // then
        assertThat(links, hasSize(1));
    }

    @Test
    public void shouldNotFind() {
        // given
        elasticLink.setText("this is some text vovatest");
        searchDao.save(elasticLink);

        // when
        searchDao.refresh();
        List<Link> links = searchDao.search("aa thi tes som vovatext");

        // then
        assertThat(links, is(empty()));
    }
}

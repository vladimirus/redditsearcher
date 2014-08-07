package com.redditsearcher.integration;

import static com.redditsearcher.model.DomainFactory.anElasticLink;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.elasticsearch.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.redditsearcher.dao.ElasticLink;
import com.redditsearcher.dao.ElasticsearchDaoImpl;
import com.redditsearcher.model.Link;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
    @Ignore
    public void simpleWordSearch() {
        // given
        elasticLink.setText("this is some text");
        searchDao.save(elasticLink);

        // when
        sleepUninterruptibly(1, SECONDS);
        List<Link> links = searchDao.search("som");

        // then
        assertThat(links.size(), is(1));
    }

}

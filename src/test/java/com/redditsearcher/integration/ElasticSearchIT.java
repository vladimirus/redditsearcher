package com.redditsearcher.integration;

import static com.redditsearcher.model.DomainFactory.anElasticLink;

import com.redditsearcher.dao.ElasticLink;
import com.redditsearcher.dao.ElasticsearchDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Integration test for elasticsearch instance.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appCtx/appCtx-dao.xml")
public class ElasticSearchIT {

    @Autowired
    private ElasticsearchDaoImpl searchDao;

    @Test
    public void performsSimpleSearch() {
        ElasticLink elasticLink = anElasticLink();
        searchDao.save(elasticLink);
        searchDao.search("test");
        searchDao.delete(elasticLink.getId());
    }

}

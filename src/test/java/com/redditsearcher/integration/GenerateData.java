package com.redditsearcher.integration;

import static com.redditsearcher.model.DomainFactory.anElasticLink;

import com.redditsearcher.dao.ElasticLink;
import com.redditsearcher.dao.ElasticsearchDaoImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Generates some data for local development.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appCtx/*.xml")
public class GenerateData {
    @Autowired
    private ElasticsearchDaoImpl elasticsearchDao;

    @Test
    @Ignore
    public void generate() {
        for (int i = 0; i < 100; i++) {
            ElasticLink link = anElasticLink();
            link.setId(String.valueOf(i));
            elasticsearchDao.save(link);
        }

        ElasticLink link = anElasticLink();
        link.setText(link.getText() + " google");
        link.setId("google");
        elasticsearchDao.save(link);
    }
}

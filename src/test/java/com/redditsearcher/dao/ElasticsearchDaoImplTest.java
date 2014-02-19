package com.redditsearcher.dao;

import org.junit.Before;
import org.junit.Test;

public class ElasticsearchDaoImplTest {
    private ElasticsearchDaoImpl dao;

    @Before
    public void before() {
        this.dao = new ElasticsearchDaoImpl();
    }

    @Test
    public void search() {

        // when
        dao.search("test");
    }
}

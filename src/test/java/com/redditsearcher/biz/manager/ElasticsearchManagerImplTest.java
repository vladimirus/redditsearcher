package com.redditsearcher.biz.manager;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.redditsearcher.dao.ElasticsearchDao;

@RunWith(MockitoJUnitRunner.class)
public class ElasticsearchManagerImplTest {
    private ElasticsearchManagerImpl manager;
    @Mock
    private ElasticsearchDao elasticsearchDao;

    @Before
    public void before() {
        this.manager = new ElasticsearchManagerImpl();
        this.manager.elasticsearchDao = elasticsearchDao;
    }

    @Test
    public void search() {

        // when
        manager.search("test");

        // then
        verify(elasticsearchDao).search("test");

    }
}

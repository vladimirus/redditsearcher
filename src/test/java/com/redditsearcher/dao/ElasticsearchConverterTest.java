package com.redditsearcher.dao;

import static com.redditsearcher.model.DomainFactory.anElasticLink;
import static com.redditsearcher.model.DomainFactory.aListOfElasticLink;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.redditsearcher.model.Link;

public class ElasticsearchConverterTest {
    private ElasticsearchConverter converter;

    @Before
    public void before() {
        this.converter = new ElasticsearchConverter();
    }

    @Test
    public void shouldConvert() {
        // given
        ElasticLink elasticLink = anElasticLink();

        // when
        Link actualLink = converter.convert(elasticLink);

        // then
        assertEquals("Some text", actualLink.getText());
        assertEquals("http://example.com", actualLink.getUri());
    }

    @Test
    public void shouldConvertMany() {
        // given
        List<ElasticLink> elasticLinks = aListOfElasticLink(10);

        // when
        List<Link> actualLinks = converter.convertList(elasticLinks);

        // then
        assertEquals(Integer.valueOf(10), (Integer) actualLinks.size());
        assertEquals("Some text", actualLinks.get(0).getText());
        assertEquals("http://example.com", actualLinks.get(0).getUri());
    }
}

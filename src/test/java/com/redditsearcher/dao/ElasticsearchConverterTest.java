package com.redditsearcher.dao;

import static com.google.common.collect.Iterables.getFirst;
import static com.redditsearcher.model.DomainFactory.aListOfElasticLink;
import static com.redditsearcher.model.DomainFactory.anElasticLink;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import com.redditsearcher.model.Link;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

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
        assertThat(actualLink.getText(), equalTo("Some text"));
        assertThat(actualLink.getUri(), equalTo("http://example.com"));
        assertThat(actualLink.getRating(), equalTo(100));
    }

    @Test
    public void shouldConvertMany() {
        // given
        Collection<ElasticLink> elasticLinks = aListOfElasticLink(10);

        // when
        Collection<Link> actualLinks = converter.convertList(elasticLinks);

        // then
        assertThat(actualLinks, hasSize(10));
        assertThat(getFirst(actualLinks, null).getText(), equalTo("Some text"));
        assertThat(getFirst(actualLinks, null).getUri(), equalTo("http://example.com"));
    }
}

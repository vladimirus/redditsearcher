package com.redditsearcher.dao;

import static com.google.common.collect.FluentIterable.from;

import com.google.common.base.Function;
import com.redditsearcher.model.Link;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Converts objects to ElasticLink and back.
 */
@Component
public class ElasticsearchConverter {

    public Link convert(ElasticLink elasticLink) {
        Link link = new Link();
        link.setText(elasticLink.getText());
        link.setUri(elasticLink.getUri());
        link.setRating(elasticLink.getRating());
        return link;
    }

    public Collection<Link> convertList(final Iterable<ElasticLink> elasticLinks) {
        return from(elasticLinks).transform(new Function<ElasticLink, Link>() {
            @Override
            public Link apply(ElasticLink elasticLink) {
                return convert(elasticLink);
            }
        }).toList();
    }
}

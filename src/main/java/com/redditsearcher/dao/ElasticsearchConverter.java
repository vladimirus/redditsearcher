package com.redditsearcher.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.redditsearcher.model.Link;

/**
 * Converts objects to ElasticLink and back.
 */
@Component
public class ElasticsearchConverter {

    public Link convert(ElasticLink elasticLink) {
        Link link = new Link();
        link.setText(elasticLink.getText());
        link.setUri(elasticLink.getUri());
        return link;
    }

    public List<Link> convertList(List<ElasticLink> elasticLinks) {
        List<Link> links = new ArrayList<>();
        for (ElasticLink elasticLink : elasticLinks) {
            Link link = convert(elasticLink);
            links.add(link);
        }
        return links;
    }
}

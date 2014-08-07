package com.redditsearcher.model;

import com.redditsearcher.dao.ElasticLink;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * It is responsible to instantiate default domain class instances.
 */
public final class DomainFactory {

    private DomainFactory() {
        // Don't instantiate
    }

    public static ElasticLink anElasticLink() {
        ElasticLink link = new ElasticLink();
        link.setUri("http://example.com");
        link.setRating(10);
        link.setText("Some text");
        link.setId("this-is-id");
        link.setCreated(new Date());
        return link;
    }

    public static List<ElasticLink> aListOfElasticLink(int numberOfLinks) {
        List<ElasticLink> list = new ArrayList<>();
        for (int i = 0; i < numberOfLinks; i++) {
            ElasticLink link = anElasticLink();
            list.add(link);
        }

        return list;
    }

}
